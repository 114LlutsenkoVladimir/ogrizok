package com.example.universityadmissionscommittee.dto.applicant;

import com.example.universityadmissionscommittee.dto.ExamRowDto;
import com.example.universityadmissionscommittee.service.CalculateAverageScoreService;

import java.util.*;
import java.util.stream.Collectors;

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
        LinkedHashMap<Long, List<ApplicantReportDto>> reportTemp = new LinkedHashMap<>();
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
                List<ApplicantReportDto> applicants = reportTemp.computeIfAbsent(
                        specialtyId, k -> new ArrayList<>());

                ApplicantReportDto applicant = applicants.stream()
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

                            newApplicant.addExamResult(subjectId, row.getScore());
                            if(benefitId != null)
                                newApplicant.addBenefit(benefitId, benefitName, benefitPoints);

                            applicants.add(newApplicant);
                            return newApplicant;
                        });
                applicant.addExamResult(subjectId, row.getScore());
                if(benefitId != null)
                    applicant.addBenefit(benefitId, benefitName, benefitPoints);
            } else {
                reportTemp.putIfAbsent(specialtyId, new ArrayList<>());
            }
        }

        // 5. finalize subjectIdsBySpecialty
        for (Map.Entry<Long, Set<Long>> entry : tempSubjectIdsBySpecialty.entrySet()) {
            subjectIdsBySpecialty.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }

        report = reportTemp.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .map(dto -> new ApplicantReportDtoWithAverageScore(
                                        dto, CalculateAverageScoreService.calculate(dto)))
                                .toList(),
                        (v1, v2) -> v1,
                        LinkedHashMap::new
                ));
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
