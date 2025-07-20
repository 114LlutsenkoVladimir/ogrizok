package com.example.universityadmissionscommittee.dto;

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

    public ExamRowDto(Long applicantId, String firstName,
                      String lastName, String phoneNumber,
                      String email,
                      Long specialtyId,
                      String specialtyName,
                      Long subjectId,
                      String subjectName,
                      Integer score) {
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

    public int getScore() {
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
}
