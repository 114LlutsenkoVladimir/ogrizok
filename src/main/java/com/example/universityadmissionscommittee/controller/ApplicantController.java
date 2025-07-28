package com.example.universityadmissionscommittee.controller;

import com.example.universityadmissionscommittee.data.*;
import com.example.universityadmissionscommittee.data.enums.ApplicantStatus;
import com.example.universityadmissionscommittee.dto.applicant.ApplicantCreateDto;
import com.example.universityadmissionscommittee.dto.applicant.ApplicantInitDto;
import com.example.universityadmissionscommittee.dto.applicant.ApplicantReportGrouped;
import com.example.universityadmissionscommittee.dto.specialty.SpecialtyIdAndNameDto;
import com.example.universityadmissionscommittee.service.ApplicantService;
import com.example.universityadmissionscommittee.service.BenefitService;
import com.example.universityadmissionscommittee.service.SpecialtyService;
import com.example.universityadmissionscommittee.service.SubjectService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
//@RequestMapping("/applicants")
public class ApplicantController {
    private ApplicantService applicantService;
    private SpecialtyService specialtyService;
    private SubjectService subjectService;
    private BenefitService benefitService;

    public ApplicantController(ApplicantService applicantService,
                               SpecialtyService specialtyService,
                               SubjectService subjectService,
                               BenefitService benefitService) {
        this.applicantService = applicantService;
        this.specialtyService = specialtyService;
        this.subjectService = subjectService;
        this.benefitService = benefitService;
    }

    @PostMapping("/addApplicant")
    public ApplicantReportGrouped addApplicant(@RequestBody ApplicantCreateDto dto) {
        Applicant applicant = applicantService.createApplicantFromDto(dto);
        Long id = applicant.getId();
        return applicantService.findApplicantById(id);
    }

    @GetMapping("/initializeApplicantPage")
    private ApplicantInitDto initialize() {
        return new ApplicantInitDto(
                benefitService.allIdAndName(),
                subjectService.allIdAndName(),
                specialtyService.allIdAndName()
        );
    }

    private ApplicantReportGrouped updateTable(Long specialtyId) {
        return applicantService.getApplicantsByOneSpecialty(specialtyId);
    }


    @DeleteMapping("/deleteApplicant/{id}")
    public ResponseEntity<Void> deleteApplicantById(@PathVariable Long id) {
        applicantService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/availableSpecialties")
    public List<SpecialtyIdAndNameDto> availableSpecialties(@RequestBody List<Long> subjectIds) {
        return specialtyService.findAvailableForSubjects(subjectIds);
    }

    @PostMapping("/updateApplicant")
    public Applicant updateApplicant(@RequestParam Long id,
                                  @RequestParam(required = false) String firstName,
                                  @RequestParam(required = false) String lastName,
                                  @RequestParam(required = false) String email,
                                  @RequestParam(required = false) String phoneNumber,
                                  @RequestParam ApplicantStatus statusType,
                                  HttpSession session) {

        Applicant applicant = applicantService.findById(id);
        if(!firstName.isEmpty())
            applicant.setFirstName(firstName);
        if(!lastName.isEmpty())
            applicant.setLastName(lastName);
        if(!email.isEmpty())
            applicant.setEmail(email);
        if(!phoneNumber.isEmpty())
            applicant.setPhoneNumber(phoneNumber);
        applicant.setStatusType(statusType);
        applicant.setStatus(statusType.toString());
        return applicantService.save(applicant);
    }

    @GetMapping("/findApplicant")
    public ApplicantReportGrouped findApplicant(
            @RequestParam(required = false) Optional<Long> id,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phoneNumber
    ) {
        ApplicantReportGrouped report = applicantService.findApplicantById(id.get());
        return report;
    }


    @GetMapping("/filterApplicantsBySpecialty/{specialtyId}")
    public ApplicantReportGrouped filterApplicants(@PathVariable Long specialtyId) {
        return updateTable(specialtyId);
    }


   
}
