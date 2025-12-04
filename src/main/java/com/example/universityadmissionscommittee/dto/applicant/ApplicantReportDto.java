package com.example.universityadmissionscommittee.dto.applicant;

import com.example.universityadmissionscommittee.dto.BenefitIdNamePoints;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ApplicantReportDto {
    private Long applicantId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Integer priority;
    private String status;

    private HashMap<Long, Integer> subjectAndScore = new HashMap<>();
    private List<BenefitIdNamePoints> benefits = new ArrayList<>();

    public ApplicantReportDto(Long applicantId, String firstName,
                              String lastName, String phoneNumber,
                              String email, Integer priority,
                              String status) {
        this.applicantId = applicantId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.priority = priority;
        this.status = status;
    }

    public Long getApplicantId() {
        return applicantId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public HashMap<Long, Integer> getSubjectAndScore() {
        return subjectAndScore;
    }

    public void addExamResult(Long subjectId, Integer score) {
        subjectAndScore.put(subjectId, score);
    }

    public Integer getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }

    public List<BenefitIdNamePoints> getBenefits() {
        return benefits;
    }

    public void addBenefit(BenefitIdNamePoints benefit) { benefits.add(benefit); }

    public void addBenefit(Long id, String name, Integer points) {
        addBenefit(new BenefitIdNamePoints(id, name, points));
    }
}
