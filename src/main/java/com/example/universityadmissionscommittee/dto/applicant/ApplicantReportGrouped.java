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

        if (examRows == null || examRows.isEmpty()) {
            return;
        }

        // Временные структуры:
        // предметы по специальности (для сохранения порядка используем LinkedHashSet)
        Map<Long, Set<Long>> subjectsPerSpecSet = new LinkedHashMap<>();
        // спец → (абитуриент → DTO) — O(1) доступ, без stream().findFirst()
        Map<Long, LinkedHashMap<Long, ApplicantReportDto>> perSpecApplicants = new LinkedHashMap<>();

        for (ExamRowDto row : examRows) {
            final Long specId = row.getSpecialtyId();
            final Long subjId = row.getSubjectId();

            // 1) Имена спец/предметов (NULL пропускаем — это норм при LEFT JOIN’ах)
            if (specId != null) {
                specialtyNames.putIfAbsent(specId, row.getSpecialtyName());
            }
            if (subjId != null) {
                subjectNames.putIfAbsent(subjId, row.getSubjectName());
            }

            // 2) Множество предметов на специальность (фиксируем даже пустые специ)
            if (specId != null) {
                subjectsPerSpecSet.computeIfAbsent(specId, k -> new LinkedHashSet<>());
                if (subjId != null) {
                    subjectsPerSpecSet.get(specId).add(subjId);
                }
            }

            // 3) Строки отчёта — только если есть заявитель (LEFT JOIN допускает null)
            final Long applicantId = row.getApplicantId();
            if (specId == null) continue;                    // без специальности — пропускаем
            if (applicantId == null) {                       // пустая спец без заявителей — но «создадим» ключ
                perSpecApplicants.computeIfAbsent(specId, k -> new LinkedHashMap<>());
                continue;
            }

            // 3.1 достаём/создаём DTO абитуриента внутри конкретной специальности
            var byApplicant = perSpecApplicants
                    .computeIfAbsent(specId, k -> new LinkedHashMap<>());

            var dto = byApplicant.computeIfAbsent(applicantId, id ->
                    new ApplicantReportDto(
                            row.getApplicantId(),
                            row.getFirstName(),
                            row.getLastName(),
                            row.getPhoneNumber(),
                            row.getEmail(),
                            row.getPriority(),
                            row.getStatus()
                    )
            );

            // 3.2 оценки (могут быть null при LEFT JOIN)
            if (subjId != null && row.getScore() != null) {
                dto.addExamResult(subjId, row.getScore());   // перезапись последним значением — предсказуемо
            }

            // 3.3 льготы (могут дублироваться из-за LEFT JOIN) — защитимся от дублей на лету
            if (row.getBenefitId() != null) {
                boolean alreadyAdded = dto.getBenefits().stream()
                        .anyMatch(b -> Objects.equals(b.id(), row.getBenefitId()));
                if (!alreadyAdded) {
                    dto.addBenefit(row.getBenefitId(), row.getBenefitName(), row.getBenefitPoints());
                }
            }
        }

        // 4) финализируем список предметов по специальности (сохраняя порядок)
        for (var e : subjectsPerSpecSet.entrySet()) {
            subjectIdsBySpecialty.put(e.getKey(), new ArrayList<>(e.getValue()));
        }

        // 5) собираем финальный отчёт и сразу считаем средний балл;
        //    при желании — сортируем внутри каждой специальности по среднему (убыв.)
        LinkedHashMap<Long, List<ApplicantReportDtoWithAverageScore>> result = new LinkedHashMap<>();
        for (var e : perSpecApplicants.entrySet()) {
            Long specId = e.getKey();
            Collection<ApplicantReportDto> applicants = e.getValue().values(); // в порядке добавления

            List<ApplicantReportDtoWithAverageScore> rows = applicants.stream()
                    .map(dto -> new ApplicantReportDtoWithAverageScore(
                            dto, CalculateAverageScoreService.calculate(dto)))
                    // сортировка по среднему (убыв.), потом по приоритету, потом по id — опционально
                    .sorted(Comparator
                            .comparingDouble(ApplicantReportDtoWithAverageScore::getAverageScore).reversed()
                            .thenComparing(a -> a.getBase().getPriority(), Comparator.nullsLast(Comparator.naturalOrder()))
                            .thenComparing(a -> a.getBase().getApplicantId()))
                    .toList();

            result.put(specId, rows);
        }
        report = result;
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
