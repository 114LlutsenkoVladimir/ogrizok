package com.example.universityadmissionscommittee.dto;

import java.util.HashMap;

public class ApplicantReportDto {
    private Long applicantId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String specialtyName;
    private HashMap<String, Integer> subjectAndScore = new HashMap<>();

    public ApplicantReportDto(Long applicantId, String firstName,
                              String lastName, String phoneNumber,
                              String email, String specialtyName) {
        this.applicantId = applicantId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.specialtyName = specialtyName;
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

    public String getSpecialtyName() {
        return specialtyName;
    }

    public HashMap<String, Integer> getSubjectAndScore() {
        return subjectAndScore;
    }

    public void addExamResult(String subject, Integer score) {
        subjectAndScore.put(subject, score);
    }
}
