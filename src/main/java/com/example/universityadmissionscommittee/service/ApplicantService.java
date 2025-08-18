package com.example.universityadmissionscommittee.service;

import com.example.universityadmissionscommittee.data.*;
import com.example.universityadmissionscommittee.data.enums.ApplicantStatus;
import com.example.universityadmissionscommittee.dto.applicant.ApplicantCreateDto;
import com.example.universityadmissionscommittee.dto.applicant.ApplicantReportGrouped;
import com.example.universityadmissionscommittee.dto.ExamRowDto;
import com.example.universityadmissionscommittee.exception.specialty.SpecialtyNotFoundException;
import com.example.universityadmissionscommittee.exception.applicant.ApplicantCreationException;
import com.example.universityadmissionscommittee.exception.applicant.ApplicantNotFoundException;
import com.example.universityadmissionscommittee.repository.ApplicantRepository;
import com.example.universityadmissionscommittee.repository.examResult.ExamResultRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return repository.findById(id).orElseThrow(ApplicantNotFoundException::new);
    }

    @Override
    public void deleteById(Long id) {
        Applicant applicant = findById(id);
        examResultRepository.deleteAll(applicant.getExamResults());
        applicant.getBenefits().clear();
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Applicant findByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(
                ApplicantNotFoundException::new
        );
    }

    @Transactional(readOnly = true)
    public Applicant findByPhoneNumber(String phoneNumber) {
        return  repository.findByPhoneNumber(phoneNumber).orElseThrow(
                ApplicantNotFoundException::new
        );
    }
    @Transactional(readOnly = true)
    public ApplicantReportGrouped getApplicantsBySpecialties(List<Long> specialtyIds) {
        List<ExamRowDto> examRows = examResultRepository.examRowData(specialtyIds);
        return new ApplicantReportGrouped(examRows);
    }

    @Transactional(readOnly = true)
    public ApplicantReportGrouped getApplicantsByOneSpecialty(Long specialtyId) {
        return getApplicantsBySpecialties(new ArrayList<>(List.of(specialtyId)));
    }

    @Transactional(readOnly = true)
    public ApplicantReportGrouped getApplicantsBySpecialtiesReport() {
        return getApplicantsBySpecialties(specialtyService.findAll()
                .stream().map(Specialty::getId).toList());
    }

    @Transactional(readOnly = true)
    public ApplicantReportGrouped findApplicantByKeyAttributes(Long applicantId,
                                                               String email,
                                                               String phoneNumber) {
        List<ExamRowDto> examRows = examResultRepository
                .findExamRowsByApplicantKeyAttributes(applicantId, email, phoneNumber);
        if(examRows.isEmpty())
            throw new ApplicantNotFoundException();

        return new ApplicantReportGrouped(examRows);
    }

    @Transactional
    public void updateApplicantStatus(Long applicantId, Long specialtyId, ApplicantStatus status) {
        Applicant applicant = findById(applicantId);
        SpecialtyForApplicant specialty = applicant.getSpecialties().stream()
                .filter(s -> Objects.equals(s.getSpecialty().getId(), specialtyId)).findFirst().orElseThrow(
                        SpecialtyNotFoundException::new
                );
        specialty.setApplicantStatus(status);
        save(applicant);
    }

    @Transactional
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
                (id) ->  {
                    applicant.addBenefit(benefitService.findById(id));
                }
        );

        dto.getSpecialtyAndPriority().forEach((id, priority) -> {
            Specialty specialty = specialtyService.findById(id);
            SpecialtyForApplicant specialtyForApplicant = new SpecialtyForApplicant(applicant, specialty, priority);
            applicant.addSpecialty(specialtyForApplicant);
        });


        dto.getSubjectAndScore().entrySet().stream()
                .filter(e -> e.getValue() != null && e.getKey() != null)
                .forEach((entry) -> {
                    ExamResult examResult =
                            new ExamResult(applicant, subjectService.findById(entry.getKey()), entry.getValue());
                    applicant.addExamResult(examResult);
                });
        return save(applicant);
    }

}
