package com.example.universityadmissionscommittee.dto.specialty;
import com.example.universityadmissionscommittee.exception.SpecialtyNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SpecialtyReportGrouped {

    Map<Long, String> facultyNames = new HashMap<>();

    Map<Long, List<SpecialtyReportDto>> report = new HashMap<>();

    public SpecialtyReportGrouped(List<SpecialtyReportDto> dtos) {
        buildReport(dtos);
    }

    private void buildReport(List<SpecialtyReportDto> dtos) {

        for (SpecialtyReportDto dto : dtos) {
            facultyNames.putIfAbsent(dto.getFacultyId(), dto.getFacultyName());
        }

        report = dtos.stream().collect(Collectors.groupingBy(SpecialtyReportDto::getFacultyId));
    }

    public Map<Long, String> getFacultyNames() {
        return facultyNames;
    }

    public Map<Long, List<SpecialtyReportDto>> getReport() {
        return report;
    }
}
