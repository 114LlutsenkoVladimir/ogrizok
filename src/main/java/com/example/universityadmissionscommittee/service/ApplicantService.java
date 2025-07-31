package com.example.universityadmissionscommittee.service;

import com.example.universityadmissionscommittee.data.*;
import com.example.universityadmissionscommittee.dto.applicant.ApplicantCreateDto;
import com.example.universityadmissionscommittee.dto.applicant.ApplicantReportGrouped;
import com.example.universityadmissionscommittee.dto.ExamRowDto;
import com.example.universityadmissionscommittee.exception.applicant.ApplicantCreationException;
import com.example.universityadmissionscommittee.exception.applicant.ApplicantNotFoundException;
import com.example.universityadmissionscommittee.repository.ApplicantRepository;
import com.example.universityadmissionscommittee.repository.examResult.ExamResultRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ApplicantService extends AbstractCrudService<Applicant, Long, ApplicantRepository> {

    private final ExamResultRepository examResultRepository;

    private final SpecialtyService specialtyService;

    private final BenefitService benefitService;

    private final SubjectService subjectService;

    protected ApplicantService(ApplicantRepository repository,
                               ExamResultRepository examResultRepository,
                               SpecialtyService specialtyService,
                               BenefitService benefitService,
                               SubjectService subjectService) {
        super(repository);
        this.examResultRepository = examResultRepository;
        this.specialtyService = specialtyService;
        this.benefitService = benefitService;
        this.subjectService = subjectService;
    }

    @Override
    public Applicant findById(Long id) {
        try {
            return super.findById(id);
        } catch (NoSuchElementException ex) {
            throw new ApplicantNotFoundException();
        }
    }

    @Override
    public void deleteById(Long id) {
        Applicant applicant = findById(id);
        examResultRepository.deleteAll(applicant.getExamResults());
        applicant.getBenefits().clear();
        repository.deleteById(id);
    }



    public Applicant findByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(
                NoSuchElementException::new
        );
    }

    public Applicant findByPhoneNumber(String phoneNumber) {
        return  repository.findByPhoneNumber(phoneNumber).orElseThrow(
                NoSuchElementException::new
        );
    }

    public ApplicantReportGrouped getApplicantsBySpecialties(List<Long> specialtyIds) {
        List<ExamRowDto> examRows = examResultRepository.examRowData(specialtyIds);
        return new ApplicantReportGrouped(examRows);
    }

    public ApplicantReportGrouped getApplicantsByOneSpecialty(Long specialtyId) {
        return getApplicantsBySpecialties(new ArrayList<>(List.of(specialtyId)));
    }

    public ApplicantReportGrouped getApplicantsBySpecialtiesReport() {
        return getApplicantsBySpecialties(specialtyService.findAll()
                .stream().map(Specialty::getId).toList());
    }

    public ApplicantReportGrouped findApplicantById(Long applicantId) {
        List<ExamRowDto> examRows = examResultRepository.findExamRowsByApplicantId(applicantId);
        ApplicantReportGrouped applicantsGrouped = new ApplicantReportGrouped(examRows);
        if(applicantsGrouped.getReport().isEmpty())
            throw new ApplicantNotFoundException();
        return applicantsGrouped;
    }

    public Applicant createApplicantFromDto(ApplicantCreateDto dto) {

        if (repository.existsByPhoneNumber(dto.getPhoneNumber()))
            throw new ApplicantCreationException("Такий номер телефона вже зайнятий");

        if (repository.existsByEmail(dto.getEmail()))
            throw new ApplicantCreationException("Така пошта вже зайнята");

        if (dto.getSpecialtyAndPriority().isEmpty())
            throw new ApplicantCreationException("Оберіть хоча б 1 спеціальність");

        Applicant applicant = new Applicant(
                dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getPhoneNumber()
        );

        dto.getBenefitIds().forEach(
                id -> applicant.addBenefit(benefitService.findById(id))
        );

        dto.getSpecialtyAndPriority().forEach((id, priority) -> {
            Specialty specialty = specialtyService.findById(id);
            SpecialtyForApplicant specialtyForApplicant = new SpecialtyForApplicant(specialty, priority);
            applicant.addSpecialty(specialtyForApplicant);
        });


        dto.getSubjectAndScore().entrySet().stream()
                .filter(e -> e.getValue() != null && e.getKey() != null)
                .forEach((entry) -> {
                    ExamResult examResult =
                            new ExamResult(applicant, subjectService.findById(entry.getKey()), entry.getValue());
                    applicant.linkExamResult(examResult);
                });

        return save(applicant);
    }

}
