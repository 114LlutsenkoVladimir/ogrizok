package com.example.universityadmissionscommittee.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ApplicantCreateDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private List<Long> specialtyIds = new ArrayList<>();
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
    public void addSpecialtyId(Long id) {
        specialtyIds.add(id);
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

    public List<Long> getSpecialtyIds() {
        return specialtyIds;
    }

    public HashMap<Long, Integer> getSubjectAndScore() {
        return subjectAndScore;
    }

    public List<Long> getBenefitIds() {
        return benefitIds;
    }
}
