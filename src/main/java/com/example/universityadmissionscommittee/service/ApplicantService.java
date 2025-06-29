package com.example.universityadmissionscommittee.service;

import com.example.universityadmissionscommittee.data.*;
import com.example.universityadmissionscommittee.dto.ApplicantCreateDto;
import com.example.universityadmissionscommittee.dto.ApplicantReportDto;
import com.example.universityadmissionscommittee.dto.ExamRowDto;
import com.example.universityadmissionscommittee.exception.applicant.ApplicantCreationException;
import com.example.universityadmissionscommittee.repository.ApplicantRepository;
import com.example.universityadmissionscommittee.repository.ExamResultRepository;
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

    public Map<String, List<ApplicantReportDto>> getApplicantsBySpecialties(List<String> specialtyNames) {

        List<ExamRowDto> examRows = examResultRepository.examRowData();
        Map<String, List<ApplicantReportDto>> report = new HashMap<>();


        for (String specialtyName : specialtyNames)
            report.putIfAbsent(specialtyName, new ArrayList<>());


        for (ExamRowDto row : examRows) {

            String specialtyName = row.getSpecialtyName();
            List<ApplicantReportDto> applicants = report.get(specialtyName);

            ApplicantReportDto applicant = applicants.stream().filter(
                            a -> a.getApplicantId().equals(row.getApplicantId())).findFirst()
                    .orElseGet( () -> {
                        ApplicantReportDto newApplicant = new ApplicantReportDto(row.getApplicantId(),
                                row.getFirstName(), row.getLastName(),
                                row.getPhoneNumber(), row.getEmail(),
                                row.getSpecialtyName()

                        );
                        applicants.add(newApplicant);
                        return newApplicant;
                    });

            applicant.addExamResult(row.getSubjectName(), row.getScore());

        }
        return report;
    }

    public Map<String, List<ApplicantReportDto>> getApplicantsByOneSpecialty(String specialtyName) {
        return getApplicantsBySpecialties(new ArrayList<>(List.of(specialtyName)));
    }

    public Map<String, List<ApplicantReportDto>> getApplicantsBySpecialtiesReport() {
        return getApplicantsBySpecialties(specialtyService.findAll()
                .stream().map(Specialty::getName).toList());
    }

    public void createApplicantFromDto(ApplicantCreateDto dto) {

        if (repository.existsByPhoneNumber(dto.getPhoneNumber()))
            throw new ApplicantCreationException("Такий номер телефона вже зайнятий");

        if (repository.existsByEmail(dto.getEmail()))
            throw new ApplicantCreationException("Така пошта вже зайнята");

        if (dto.getSpecialtyIds().isEmpty())
            throw new ApplicantCreationException("Оберіть хоча б 1 спеціальність");

        Applicant applicant = new Applicant(
                dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getPhoneNumber()
        );

        dto.getBenefitIds().forEach(
                id -> applicant.addBenefit(benefitService.findById(id))
        );

        dto.getSpecialtyIds().forEach(id -> applicant.addSpecialty(specialtyService.findById(id)));


        dto.getSubjectAndScore().entrySet().stream()
                .filter(e -> e.getValue() != null && e.getKey() != null)
                .forEach((entry) -> {
                    ExamResult examResult =
                            new ExamResult(applicant, subjectService.findById(entry.getKey()), entry.getValue());
                    applicant.linkExamResult(examResult);
                });

        save(applicant);
    }

}
