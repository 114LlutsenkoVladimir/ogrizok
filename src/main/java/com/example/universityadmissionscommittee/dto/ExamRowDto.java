package com.example.universityadmissionscommittee.dto;

import com.example.universityadmissionscommittee.data.enums.ApplicantStatus;

public class ExamRowDto {
    private Long applicantId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Long specialtyId;
    private String specialtyName;
    private Long subjectId;
    private String subjectName;
    private Integer score;
    private Integer priority;
    private ApplicantStatus status;
    private Long benefitId;
    private String benefitName;
    private Integer benefitPoints;

    public ExamRowDto(Long applicantId, String firstName,
                      String lastName, String phoneNumber,
                      String email,
                      Long specialtyId,
                      String specialtyName,
                      Long subjectId,
                      String subjectName,
                      Integer score, Integer priority,
                      ApplicantStatus status,
                      Long benefitId,
                      String benefitName,
                      Integer benefitPoints) {
        this.applicantId = applicantId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.specialtyId = specialtyId;
        this.specialtyName = specialtyName;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.score = score;
        this.priority = priority;
        this.status = status;
        this.benefitId = benefitId;
        this.benefitName = benefitName;
        this.benefitPoints = benefitPoints;
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

    public String getSubjectName() {
        return subjectName;
    }

    public Integer getScore() {
        return score;
    }

    public Long getSpecialtyId() {
        return specialtyId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public String getSpecialtyName() {
        return specialtyName;
    }

    public Integer getPriority() { return priority; }

    public String getStatus() {  return status != null ? status.toString() : null; }

    public Long getBenefitId() {
        return benefitId;
    }

    public String getBenefitName() {
        return benefitName;
    }

    public Integer getBenefitPoints() {
        return benefitPoints;
    }
}
