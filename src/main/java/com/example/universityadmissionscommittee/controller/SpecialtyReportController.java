package com.example.universityadmissionscommittee.controller;

import com.example.universityadmissionscommittee.service.FacultyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/specialtiesReport")
public class SpecialtyReportController {
    private FacultyService facultyService;

    public SpecialtyReportController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("faculties", facultyService.findAll());
        return "specialtiesReport";
    }

}
