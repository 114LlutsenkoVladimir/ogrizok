package com.example.universityadmissionscommittee.controller;

import com.example.universityadmissionscommittee.data.Specialty;
import com.example.universityadmissionscommittee.data.Subject;
import com.example.universityadmissionscommittee.service.FacultyService;
import com.example.universityadmissionscommittee.service.ReportService;
import com.example.universityadmissionscommittee.service.SpecialtyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/report")
public class ReportController {

    private FacultyService facultyService;

    private ReportService reportService;

    private SpecialtyService specialtyService;

    public ReportController(FacultyService facultyService, ReportService
            reportService, SpecialtyService specialtyService) {
        this.facultyService = facultyService;
        this.reportService = reportService;
        this.specialtyService = specialtyService;
    }

    @GetMapping("/")
    public String showForm() {
        return "report";
    }

    @GetMapping("/specialtiesReport")
    public String showForm(Model model) {
        model.addAttribute("faculties", facultyService.findAll());
        return "specialtiesReport";
    }

    @RequestMapping("/applicantsReport")
    public String applicantsReport(Model model) {
        model.addAttribute("report",
                reportService.getApplicantsBySpecialtiesReport());

        Map<String, List<String>> subjectBySpecialties = new HashMap<>();
        List<Specialty> specialties = specialtyService.findAll();

        for (Specialty s : specialties) {
            subjectBySpecialties.put(s.getName(),
                    s.getNeededSubjects().stream().map(Subject::getName).collect(Collectors.toList()));
            for (Subject subject : s.getNeededSubjects()) {
                System.out.println(subject.getName());
            }
        }
        model.addAttribute("subjectBySpecialties", subjectBySpecialties);
        return "test";
    }
}
