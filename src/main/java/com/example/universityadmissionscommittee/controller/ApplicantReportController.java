package com.example.universityadmissionscommittee.controller;

import com.example.universityadmissionscommittee.data.Specialty;
import com.example.universityadmissionscommittee.data.Subject;
import com.example.universityadmissionscommittee.service.SpecialtyService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/applicantReport")
public class ApplicantReportController {
    public SpecialtyService specialtyService;

    public ReportService reportService;

    public ApplicantReportController(SpecialtyService specialtyService, ReportService reportService) {
        this.specialtyService = specialtyService;
        this.reportService = reportService;
    }

    @GetMapping("/")
    public String showForm(HttpSession session) {
        session.setAttribute("specialties", specialtyService.findAll());
        return "applicantsReport";
    }

    @RequestMapping("/test")
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
