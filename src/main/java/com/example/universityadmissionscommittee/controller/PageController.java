package com.example.universityadmissionscommittee.controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PageController {



    public PageController() {}

    @GetMapping("/")
    public String initial(HttpSession session) {
        session.setAttribute("user", "user");
        return "redirect:/applicants/";
    }

    @GetMapping("/applicants/")
    public String applicantPage(HttpSession session) {
        return switch ((String) session.getAttribute("user")) {
            case "admin"     -> "applicants/admin-page";
            case "committee" -> "applicants/committee-page";
            default          -> "applicants/page-for-applicant";
        };

    }

    @GetMapping("/specialties/")
    public String specialtyPage(HttpSession session) {
        return switch ((String) session.getAttribute("user")) {
            case "admin"     -> "specialties/admin-page";
            case "committee" -> "specialties/committee-page";
            default          -> "redirect:/applicants/";
        };
    }

    @GetMapping("/users/")
    public String usersPage() {
        return "autorization";
    }


    @GetMapping("/reports/")
    public String reportPage(HttpSession session) {
        return switch ((String) session.getAttribute("user")) {
            case "admin", "committee" -> "reports/report";
            default -> "redirect:/applicants/";
        };
    }
}
