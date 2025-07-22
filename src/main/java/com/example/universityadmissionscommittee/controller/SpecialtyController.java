package com.example.universityadmissionscommittee.controller;

import com.example.universityadmissionscommittee.dto.specialty.SpecialtyReportGrouped;
import com.example.universityadmissionscommittee.service.FacultyService;
import com.example.universityadmissionscommittee.service.SpecialtyService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/specialties")
public class SpecialtyController {

    private SpecialtyService specialtyService;

    private FacultyService facultyService;

    public SpecialtyController(SpecialtyService specialtyService,
                               FacultyService facultyService) {
        this.specialtyService = specialtyService;
        this.facultyService = facultyService;
    }

    @GetMapping("/")
    public String showForm(HttpSession session) {

        return "specialties";
    }

    @PostMapping("/updateSpecialty/{id}")
    public String updateSpecialty(@PathVariable Long id) {
        return "specialties";
    }

    @GetMapping("/filterSpecialtiesByFaculty/{facultyId}")
    public SpecialtyReportGrouped selectFaculty(@PathVariable Long facultyId) {
        return updateTable(facultyId);
    }

    @GetMapping("/findSpecialty/{id}")
    public SpecialtyReportGrouped findSpecialty(@PathVariable Long id) {
        return specialtyService.findSpecialtyReportDtoById(id);
    }

    public SpecialtyReportGrouped updateTable(Long facultyId) {
        return specialtyService.getSpecialtiesByOneFaculty(facultyId);
    }
}
