package com.example.universityadmissionscommittee.controller;

import com.example.universityadmissionscommittee.data.Specialty;
import com.example.universityadmissionscommittee.data.Subject;
import com.example.universityadmissionscommittee.service.ApplicantService;
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

    private ApplicantService applicantService;

    private SpecialtyService specialtyService;



    public ApplicantReportController(SpecialtyService specialtyService,
                                     ApplicantService applicantService) {
        this.specialtyService = specialtyService;
        this.applicantService = applicantService;
    }

    @GetMapping("/")
    public String showForm(HttpSession session) {
        session.setAttribute("specialties", specialtyService.findAll());
        return "applicantsReport";
    }

    @RequestMapping("/test")
    public String applicantsReport(Model model) {
        model.addAttribute("report",
                applicantService.getApplicantsBySpecialtiesReport());

        Map<String, List<String>> subjectBySpecialties = new HashMap<>();
        List<Specialty> specialties = specialtyService.findAll();

        for (Specialty s : specialties) {
            subjectBySpecialties.put(s.getName(),
                    s.getNeededSubjects().stream().map(Subject::getName).collect(Collectors.toList()));
        }
        model.addAttribute("subjectBySpecialties", subjectBySpecialties);
        return "test";
    }



}
