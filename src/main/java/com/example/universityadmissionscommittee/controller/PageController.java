package com.example.universityadmissionscommittee.controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PageController {



    public PageController() {}

    @GetMapping("/")
    public String initial(HttpSession session) {
        session.setAttribute("role", "user");
        return "redirect:applicants/";
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

    @GetMapping("/users/")
    public String usersPage() {
        return "autorization";
    }


}
