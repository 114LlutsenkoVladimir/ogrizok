package com.example.universityadmissionscommittee.dto.applicant;

import com.example.universityadmissionscommittee.dto.ExamRowDto;
import com.example.universityadmissionscommittee.service.CalculateAverageScoreService;

import java.util.*;

public class ApplicantReportGrouped {
    private Map<Long, String> specialtyNames = new HashMap<>();
    private Map<Long, String> subjectNames = new HashMap<>();
    private Map<Long, List<Long>> subjectIdsBySpecialty = new HashMap<>();
    private LinkedHashMap<Long, List<ApplicantReportDtoWithAverageScore>> report = new LinkedHashMap<>();


    public ApplicantReportGrouped(List<ExamRowDto> examRows) {
        buildFrom(examRows);
    }

    public void buildFrom(List<ExamRowDto> examRows) {
        Map<Long, Set<Long>> tempSubjectIdsBySpecialty = new LinkedHashMap<>();

        for (ExamRowDto row : examRows) {
            Long specialtyId = row.getSpecialtyId();
            Long subjectId = row.getSubjectId();

            Long benefitId = row.getBenefitId();
            String benefitName = row.getBenefitName();
            Integer benefitPoints = row.getBenefitPoints();

            // 1. specialty name
            specialtyNames.putIfAbsent(specialtyId, row.getSpecialtyName());

            // 2. subject name
            subjectNames.putIfAbsent(subjectId, row.getSubjectName());

            // 3. subject IDs per specialty
            tempSubjectIdsBySpecialty
                    .computeIfAbsent(specialtyId, k -> new LinkedHashSet<>())
                    .add(subjectId);

            // 4. report rows — добавлять только если есть заявитель
            if (row.getApplicantId() != null) {
                List<ApplicantReportDtoWithAverageScore> applicants = report.computeIfAbsent(
                        specialtyId, k -> new ArrayList<>());

                ApplicantReportDtoWithAverageScore applicant = applicants.stream()
                        .filter(a -> Objects.equals(a.getApplicantId(), row.getApplicantId()))
                        .findFirst()
                        .orElseGet(() -> {
                            ApplicantReportDto newApplicant = new ApplicantReportDto(
                                    row.getApplicantId(),
                                    row.getFirstName(),
                                    row.getLastName(),
                                    row.getPhoneNumber(),
                                    row.getEmail(),
                                    row.getPriority(),
                                    row.getStatus()
                            );
                            ApplicantReportDtoWithAverageScore newApplicantWithScore =
                                    new ApplicantReportDtoWithAverageScore(newApplicant,
                                            CalculateAverageScoreService.calculate(newApplicant));
                            applicants.add(newApplicantWithScore);
                            return newApplicantWithScore;
                        });

                applicant.getBase().addExamResult(subjectId, row.getScore());
                applicant.getBase().addBenefit(benefitId, benefitName, benefitPoints);
            } else {
                // Обеспечить, что даже если нет заявителей, специальность появится в report с пустым списком
                report.putIfAbsent(specialtyId, new ArrayList<>());
            }
        }

        // 5. finalize subjectIdsBySpecialty
        for (Map.Entry<Long, Set<Long>> entry : tempSubjectIdsBySpecialty.entrySet()) {
            subjectIdsBySpecialty.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
    }

    public Map<Long, String> getSpecialtyNames() {
        return specialtyNames;
    }

    public Map<Long, String> getSubjectNames() {
        return subjectNames;
    }

    public Map<Long, List<Long>> getSubjectIdsBySpecialty() {
        return subjectIdsBySpecialty;
    }

    public LinkedHashMap<Long, List<ApplicantReportDtoWithAverageScore>> getReport() {
        return report;
    }
}
