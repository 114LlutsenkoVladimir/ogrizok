package com.example.universityadmissionscommittee.service;
import com.example.universityadmissionscommittee.data.ExamResult;
import com.example.universityadmissionscommittee.data.Specialty;
import com.example.universityadmissionscommittee.dto.ApplicantReportDto;
import com.example.universityadmissionscommittee.dto.ExamRowDto;
import com.example.universityadmissionscommittee.dto.SpecialtyReportDto;
import com.example.universityadmissionscommittee.repository.ApplicantRepository;
import com.example.universityadmissionscommittee.repository.SpecialtyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class ReportService {
    private SpecialtyService specialtyService;
    private ExamResultService examResultService;


    public ReportService(ExamResultService examResultService,
                         SpecialtyService specialtyService) {
        this.examResultService = examResultService;
        this.specialtyService = specialtyService;
    }

    public Map<String, List<ApplicantReportDto>> getApplicantsBySpecialties(List<String> specialtyNames) {

        List<ExamRowDto> examRows = examResultService.getExamRowData();
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


    public Map<String, List<SpecialtyReportDto>> getSpecialtiesByFaculties(List<String> facultyNames) {
        List<SpecialtyReportDto> specialties = specialtyService.specialtiesByFacultiesData(facultyNames);
        return specialties.stream()
                .collect(Collectors.groupingBy(SpecialtyReportDto::getFacultyName));
    }

    public Map<String, List<SpecialtyReportDto>> getSpecialtiesByOneFaculty(String facultyName) {
        return getSpecialtiesByFaculties(new ArrayList<>(List.of(facultyName)));
    }

    public Map<String, List<SpecialtyReportDto>> getSpecialtiesByFacultiesReport() {
        return getSpecialtiesByFaculties(specialtyService.findAll().stream().map(Specialty::getName).toList());
    }




}
