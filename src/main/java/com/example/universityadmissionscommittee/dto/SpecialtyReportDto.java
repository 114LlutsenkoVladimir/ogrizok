package com.example.universityadmissionscommittee.dto;

import com.example.universityadmissionscommittee.data.Faculty;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class SpecialtyReportDto {
    private Long id;
    private String name;
    private Integer number;
    private String  facultyName;
    private Integer numberOfBudgetPlaces;
    private Integer numberOfContractPlaces;
    private Integer sumOfPlaces;

    public SpecialtyReportDto(Long id, String name,
                              Integer number, String facultyName,
                              Integer numberOfBudgetPlaces,
                              Integer numberOfContractPlaces,
                              Integer sumOfPlaces) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.facultyName = facultyName;
        this.numberOfBudgetPlaces = numberOfBudgetPlaces;
        this.numberOfContractPlaces = numberOfContractPlaces;
        this.sumOfPlaces = sumOfPlaces;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getNumber() {
        return number;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public Integer getNumberOfBudgetPlaces() {
        return numberOfBudgetPlaces;
    }

    public Integer getNumberOfContractPlaces() {
        return numberOfContractPlaces;
    }
    public Integer getSumOfPlaces() {
        return sumOfPlaces;
    }
}
