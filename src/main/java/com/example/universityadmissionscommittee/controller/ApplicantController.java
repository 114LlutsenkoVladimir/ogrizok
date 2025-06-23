package com.example.universityadmissionscommittee.controller;

import com.example.universityadmissionscommittee.data.*;
import com.example.universityadmissionscommittee.data.enums.ApplicantStatus;
import com.example.universityadmissionscommittee.dto.ApplicantCreateDto;
import com.example.universityadmissionscommittee.dto.SpecialtyIdAndNameDto;
import com.example.universityadmissionscommittee.service.ApplicantService;
import com.example.universityadmissionscommittee.service.BenefitService;
import com.example.universityadmissionscommittee.service.SpecialtyService;
import com.example.universityadmissionscommittee.service.SubjectService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
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

    @GetMapping("/")
    public String index(HttpSession session) {
        session.setAttribute("specialties", specialtyService.findAll());
        Specialty specialty = specialtyService.findById(1L);
        session.setAttribute("subjects", specialty.getNeededSubjects());
        session.setAttribute("selectedSpecialtyId", 1L);
        updateTable(session);
        session.setAttribute("allBenefits", benefitService.findAll());
        session.setAttribute("selectedBenefitsIds", new ArrayList<>());
        session.setAttribute("statusTypes", ApplicantStatus.values());
        return "applicants/page-for-applicant";
    }


    @PostMapping("/addApplicant")
    public String addApplicant(@ModelAttribute ApplicantCreateDto dto,
                               HttpSession session) {

        Applicant applicant = new Applicant(
                dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getPhoneNumber()
        );

        dto.getBenefitIds().forEach(
                id -> applicant.addBenefit(benefitService.findById(id))
        );

        dto.getSpecialtyIds().forEach(
                id -> applicant.addSpecialty(specialtyService.findById(id))
        );

        dto.getSubjectAndScore().forEach((subjectId, score) -> {
                ExamResult examResult =
                        new ExamResult(applicant, subjectService.findById(subjectId), score);
                applicant.linkExamResult(examResult);
        });

        applicantService.save(applicant);
        updateTable(session);
        return "applicants/page-for-applicant";
    }

    private void updateTable(HttpSession session) {
        Long id = (Long) session.getAttribute("selectedSpecialtyId");
        Specialty specialty = specialtyService.findById(id);
        session.setAttribute("report",
                applicantService.getApplicantsByOneSpecialty(specialty.getName()));
    }

    @GetMapping("/selectSpecialty")
    public String selectSpecialty(@RequestParam Long specialtyId, HttpSession session) {
        session.setAttribute("selectedSpecialtyId", specialtyId);
        Specialty specialty = specialtyService.findById(specialtyId);
        session.setAttribute("subjects", specialty.getNeededSubjects());
        updateTable(session);
        return "applicants/page-for-applicant";
    }

    @DeleteMapping("/deleteApplicant")
    public String deleteApplicantById(@RequestParam Long id, HttpSession session) {
        applicantService.deleteById(id);
        updateTable(session);
        return "applicants/page-for-applicant";
    }


    @GetMapping("/selectBenefits")
    public String processSelectedBenefits(@RequestParam(required = false) List<Long> benefits,
                                          HttpSession session) {
        if(benefits== null)
            benefits = new ArrayList<>();
        session.setAttribute("selectedBenefitsIds", benefits);
        return "applicants/page-for-applicant";
    }

    @PostMapping("/availableSpecialties")
    @ResponseBody
    public List<SpecialtyIdAndNameDto> availableSpecialties(@RequestBody List<Long> subjectIds) {
        return specialtyService.findAvailableForSubjects(subjectIds);
    }

    @PostMapping("/updateApplicant")
    public String updateApplicant(@RequestParam Long id,
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
        applicantService.save(applicant);
        updateTable(session);
        return "applicants/page-for-applicant";
    }

    @PostMapping("/findApplicant")
    public String findApplicant(@RequestParam(required = false) Optional<Long> id,
                                @RequestParam(required = false) String email,
                                @RequestParam(required = false) String phoneNumber,
                                HttpSession session) {

        List<Applicant> applicants = new ArrayList<>();

        try {
            if(id.isPresent())
                applicants.add(applicantService.findById(id.get()));
            else if (!email.isEmpty())
                applicants.add(applicantService.findByEmail(email));
            else if (!phoneNumber.isEmpty())
                applicants.add(applicantService.findByPhoneNumber(phoneNumber));
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        session.setAttribute("applicants", applicants);
        return "applicants/page-for-applicant";
    }


   
}
