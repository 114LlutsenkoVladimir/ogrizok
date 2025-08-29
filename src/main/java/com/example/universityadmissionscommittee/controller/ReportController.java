package com.example.universityadmissionscommittee.controller;

import com.example.universityadmissionscommittee.dto.applicant.ApplicantReportGrouped;
import com.example.universityadmissionscommittee.dto.specialty.SpecialtyReportGrouped;
import com.example.universityadmissionscommittee.service.ApplicantService;
import com.example.universityadmissionscommittee.service.SpecialtyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/reports")
@RestController
public class ReportController {
    private ApplicantService applicantService;
    private SpecialtyService specialtyService;

    public ReportController(ApplicantService applicantService,
                            SpecialtyService specialtyService) {
        this.applicantService = applicantService;
        this.specialtyService = specialtyService;
    }

    @GetMapping("/applicantsReport")
    public ApplicantReportGrouped applicantsReport() {
        return applicantService.getApplicantsBySpecialtiesReport();
    }

    @GetMapping("/specialtiesReport")
    public SpecialtyReportGrouped specialtiesReport() {
        return specialtyService.getSpecialtiesByFacultiesReport();
    }
}
