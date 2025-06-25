package com.example.universityadmissionscommittee.service;

import com.example.universityadmissionscommittee.data.Applicant;
import com.example.universityadmissionscommittee.data.Benefit;
import com.example.universityadmissionscommittee.data.Specialty;
import com.example.universityadmissionscommittee.dto.ApplicantReportDto;
import com.example.universityadmissionscommittee.dto.ExamRowDto;
import com.example.universityadmissionscommittee.repository.ApplicantRepository;
import com.example.universityadmissionscommittee.repository.ExamResultRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ApplicantService extends AbstractCrudService<Applicant, Long, ApplicantRepository> {

    private final ExamResultRepository examResultRepository;

    private final SpecialtyService specialtyService;

    protected ApplicantService(ApplicantRepository repository,
                               ExamResultRepository examResultRepository,
                               SpecialtyService specialtyService) {
        super(repository);
        this.examResultRepository = examResultRepository;
        this.specialtyService = specialtyService;
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


}
