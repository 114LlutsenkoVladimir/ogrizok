package com.example.universityadmissionscommittee.controller;

import com.example.universityadmissionscommittee.service.ApplicantService;
import com.example.universityadmissionscommittee.service.BenefitService;
import com.example.universityadmissionscommittee.service.SpecialtyService;
import com.example.universityadmissionscommittee.service.SubjectService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class PageController {

    private ApplicantService applicantService;
    private SpecialtyService specialtyService;
    private SubjectService subjectService;
    private BenefitService benefitService;


    public PageController(ApplicantService applicantService,
                          SpecialtyService specialtyService,
                          SubjectService subjectService,
                          BenefitService benefitService) {
        this.applicantService = applicantService;
        this.specialtyService = specialtyService;
        this.subjectService = subjectService;
        this.benefitService = benefitService;
    }

    @GetMapping("/")
    public String showForm(HttpSession session, Model model) {

        session.setAttribute("specialties", specialtyService.findAll());
        session.setAttribute("allSubjects", subjectService.findAll());
        session.setAttribute("allBenefits", benefitService.findAll());
        session.setAttribute("selectedBenefitsIds", new ArrayList<>());
        session.setAttribute("selectedSpecialtyId", 1L);

        model.addAttribute("report",
                applicantService.getApplicantsByOneSpecialty(1L));
        return "applicants/page-for-applicant";
    }
}
