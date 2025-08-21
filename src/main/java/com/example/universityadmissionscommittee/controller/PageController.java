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



    public PageController() {}

    @GetMapping("/")
    public String initial(HttpSession session) {
        session.setAttribute("role", "user");
        return "applicants/page-for-applicant";
    }

    @GetMapping("/applicants/")
    public String applicantPage(HttpSession session) {
        switch ((String) session.getAttribute("role")) {
            case "admin" -> {
                return "applicants/admin-page";
            }
            case "committee" -> {
                return "applicants/committee-page";
            }
            default -> {
                return "applicants/page-for-applicant";
            }
        }

    }

    @GetMapping("/specialties/")
    public String specialtyPage(HttpSession session) {
        switch ((String) session.getAttribute("role")) {
            case "admin" -> {
                return "specialties/admin-page";
            }
            case "committee" -> {
                return "specialties/committee-page";
            }
            default -> {
                return "applicants/page-for-applicant";
            }
        }

    }


}
