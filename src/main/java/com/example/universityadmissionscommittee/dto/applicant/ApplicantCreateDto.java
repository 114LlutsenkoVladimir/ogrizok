package com.example.universityadmissionscommittee.dto.applicant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ApplicantCreateDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private HashMap<Long, Integer>  specialtyAndPriority = new HashMap<>();
    private List<Long> benefitIds = new ArrayList<>();
    private HashMap<Long, Integer> subjectAndScore = new HashMap<>();
    public ApplicantCreateDto(String firstName, String lastName,
                              String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public void addSubjectAndScore(Long subjectId, Integer score) {
        subjectAndScore.put(subjectId, score);
    }

    public void addSpecialtyAndPriority(Long id, Integer priority) {
        specialtyAndPriority.put(id, priority);
    }

    public void addBenefit(Long id) {
        benefitIds.add(id);
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

    public HashMap<Long, Integer> getSpecialtyAndPriority() {
        return specialtyAndPriority;
    }
    public HashMap<Long, Integer> getSubjectAndScore() {
        return subjectAndScore;
    }

    public List<Long> getBenefitIds() {
        return benefitIds;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSpecialtyAndPriority(HashMap<Long, Integer> specialtyAndPriority) {
        this.specialtyAndPriority = specialtyAndPriority;
    }

    public void setBenefitIds(List<Long> benefitIds) {
        this.benefitIds = benefitIds;
    }

    public void setSubjectAndScore(HashMap<Long, Integer> subjectAndScore) {
        this.subjectAndScore = subjectAndScore;
    }
}
