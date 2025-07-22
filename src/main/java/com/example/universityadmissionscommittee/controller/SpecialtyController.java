package com.example.universityadmissionscommittee.controller;

import com.example.universityadmissionscommittee.data.Faculty;
import com.example.universityadmissionscommittee.data.Specialty;
import com.example.universityadmissionscommittee.dto.specialty.SpecialtyReportDto;
import com.example.universityadmissionscommittee.service.FacultyService;
import com.example.universityadmissionscommittee.service.SpecialtyService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
        session.setAttribute("specialtiesByFaculty",
                specialtyService.getSpecialtiesByOneFaculty("Медицина"));
        session.setAttribute("faculties", facultyService.findAll());
        session.setAttribute("selectedFacultyId", 1L);
        updateTable(session);
        return "specialties";
    }

    @PostMapping("/updateSpecialty/{id}")
    public String updateSpecialty(@PathVariable Long id) {

        return "specialties";
    }

    @GetMapping("/selectFaculty")
    public String selectFaculty(HttpSession session,
                                @RequestParam Long facultyId) {
        session.setAttribute("selectedFacultyId", facultyId);
        updateTable(session);
        return "specialties";
    }

    @PostMapping("/findSpecialty")
    public String findSpecialty(@RequestParam(required = false) Optional<Long> id,
                                @RequestParam(required = false) String name,
                                HttpSession session) {
        List<Specialty> specialties = new ArrayList<>();

        try {
            if(id.isPresent())
                specialties.add(specialtyService.findById(id.get()));
            else if (!name.isEmpty())
                specialties.add(specialtyService.findByName(name));
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        session.setAttribute("specialties", specialties);
        return "specialties";
    }

    public void updateTable(HttpSession session) {
        Faculty faculty = facultyService.findById((Long)session.getAttribute("selectedFacultyId"));
        Map<String, List<SpecialtyReportDto>> specialties =
                specialtyService.getSpecialtiesByOneFaculty(
                        faculty.getName()
                );
        session.setAttribute("specialtiesByFaculty", specialties);
    }
}
