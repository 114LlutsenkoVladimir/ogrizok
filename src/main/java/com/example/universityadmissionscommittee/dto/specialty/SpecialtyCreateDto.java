package com.example.universityadmissionscommittee.dto.specialty;

import java.util.HashSet;
import java.util.Set;

public class SpecialtyCreateDto {

    private String name;

    private Integer number;

    private Long facultyId;

    private Integer budgetPlaces = 0;

    private Integer contractPlaces = 0;

    private Set<Long> subjectIds = new HashSet<>();

    protected SpecialtyCreateDto() {
    }

    public void addSubjectId(Long id) {
        subjectIds.add(id);
    }

    public String getName() {
        return name;
    }

    public Integer getNumber() {
        return number;
    }

    public Long getFacultyId() {
        return facultyId;
    }

    public Integer getBudgetPlaces() {
        return budgetPlaces;
    }

    public Integer getContractPlaces() {
        return contractPlaces;
    }

    public Set<Long> getSubjectIds() {
        return subjectIds;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    public void setBudgetPlaces(Integer budgetPlaces) {
        this.budgetPlaces = budgetPlaces;
    }

    public void setContractPlaces(Integer contractPlaces) {
        this.contractPlaces = contractPlaces;
    }

    public void setSubjectIds(Set<Long> subjectIds) {
        this.subjectIds = subjectIds;
    }
}
