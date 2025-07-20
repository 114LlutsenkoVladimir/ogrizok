package com.example.universityadmissionscommittee.dto.applicant;

import java.util.HashMap;

public class ApplicantReportDto {
    private Long applicantId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private HashMap<Long, Integer> subjectAndScore = new HashMap<>();

    public ApplicantReportDto(Long applicantId, String firstName,
                              String lastName, String phoneNumber,
                              String email) {
        this.applicantId = applicantId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
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
}
