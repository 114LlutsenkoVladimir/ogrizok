package com.example.universityadmissionscommittee.controller;

import com.example.universityadmissionscommittee.data.*;
import com.example.universityadmissionscommittee.data.enums.ApplicantStatus;
import com.example.universityadmissionscommittee.data.enums.SpecialtyType;
import com.example.universityadmissionscommittee.dto.ApplicantCreateDto;
import com.example.universityadmissionscommittee.service.ApplicantService;
import com.example.universityadmissionscommittee.service.BenefitService;
import com.example.universityadmissionscommittee.service.SpecialtyService;
import com.example.universityadmissionscommittee.service.SubjectService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

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
        return "index";
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
        return "page-for-applicant";
    }

    private void updateTable(HttpSession session) {
        session.setAttribute("applicants", applicantService.getSortedApplicantListWithSpecialty(
                specialtyService.findById((Long) session.getAttribute("selectedSpecialtyId"))
        ));
    }


    @GetMapping("/enterExamResults")
    public String enterExamResults(HttpSession session,
                                   @RequestParam Map<String, String> allParams) {
        Map<Long, Integer> results = new HashMap<>();
        allParams.forEach((key, value) -> {
            if (key.startsWith("results[")) {
                Long subjectId = Long.parseLong(key.replaceAll("[^0-9]", ""));
                Integer score = Integer.valueOf(value);
                results.put(subjectId, score);
            }
        });

        List<Specialty> matching = specialtyService.findAll().stream()
                .filter(s -> s.getNeededSubjects().stream()
                        .map(Subject::getId)
                        .allMatch(results::containsKey)) // все нужные предметы есть
                .toList();

        session.setAttribute("matchingSpecialties", matching);
        session.setAttribute("enteredResults", results);
        return "index";
    }

    @GetMapping("/selectSpecialty")
    public String selectSpecialty(@RequestParam Long specialtyId, HttpSession session) {
        session.setAttribute("selectedSpecialtyId", specialtyId);
        Specialty specialty = specialtyService.findById(specialtyId);
        session.setAttribute("subjects", specialty.getNeededSubjects());
        updateTable(session);
        return "index";
    }

    @DeleteMapping("/deleteApplicant")
    public String deleteApplicantById(@RequestParam Long id, HttpSession session) {
        applicantService.deleteById(id);
        updateTable(session);
        return "index";
    }


    @GetMapping("/getExamResults")
    public String getExamResults(HttpSession session) {
        Specialty specialty = specialtyService.findById((Long) session.getAttribute("selectedSpecialtyId"));
        session.setAttribute("subjects", specialty.getNeededSubjects());
        return "index";
    }


    @GetMapping("/selectBenefits")
    public String processSelectedBenefits(@RequestParam(required = false) List<Long> benefits,
                                          HttpSession session) {
        if(benefits== null)
            benefits = new ArrayList<>();
        session.setAttribute("selectedBenefitsIds", benefits);
        return "index";
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
        return "index";
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
        return "index";
    }


   
}
