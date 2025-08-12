package com.example.universityadmissionscommittee.controller;

import com.example.universityadmissionscommittee.data.Specialty;
import com.example.universityadmissionscommittee.dto.specialty.SpecialtyCreateDto;
import com.example.universityadmissionscommittee.dto.specialty.SpecialtyInitDto;
import com.example.universityadmissionscommittee.dto.specialty.SpecialtyReportGrouped;
import com.example.universityadmissionscommittee.service.FacultyService;
import com.example.universityadmissionscommittee.service.SpecialtyService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping("/initializeSpecialtyPage")
    public SpecialtyInitDto initialize() {
        return new SpecialtyInitDto(facultyService.allIdAndName());
    }

    @GetMapping("/updateSpecialtyPlaces")
    public SpecialtyReportGrouped updateSpecialty(
            @RequestParam Long id,
            @RequestParam(required = false) Optional<Integer> budgetPlaces,
            @RequestParam(required = false) Optional<Integer> contractPlaces) {

        specialtyService.updateSpecialtyPlaces(id, budgetPlaces, contractPlaces);
        return specialtyService.findSpecialtyReportDtoById(id, null, null);
    }

    @GetMapping("/filterSpecialtiesByFaculty/{facultyId}")
    public SpecialtyReportGrouped selectFaculty(@PathVariable Long facultyId) {
        return specialtyService.getSpecialtiesByOneFaculty(facultyId);
    }

    @GetMapping("/findSpecialty")
    public SpecialtyReportGrouped findSpecialty(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer number) {
        return specialtyService.findSpecialtyReportDtoById(id, name, number);
    }

    @GetMapping("/createSpecialty")
    public SpecialtyReportGrouped createSpecialty(@RequestBody SpecialtyCreateDto dto) {
        Specialty specialty = specialtyService.createFromDto(dto);
        return specialtyService.findSpecialtyReportDtoById(specialty.getId(), null, null);
    }
}
