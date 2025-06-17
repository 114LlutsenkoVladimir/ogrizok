package com.example.universityadmissionscommittee.dto;

public class ExamRowDto {
    private Long applicantId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String specialtyName;
    private String subjectName;
    private Integer score;

    public ExamRowDto(Long applicantId, String firstName,
                      String lastName, String phoneNumber,
                      String email, String specialtyName, String subjectName, Integer score) {
        this.applicantId = applicantId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.specialtyName = specialtyName;
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

    public String getSpecialtyName() {
        return specialtyName;
    }
}
