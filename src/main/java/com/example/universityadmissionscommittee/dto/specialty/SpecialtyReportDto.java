package com.example.universityadmissionscommittee.dto.specialty;

public class SpecialtyReportDto {
    private Long id;
    private Long facultyId;
    private String name;
    private Integer number;
    private String  facultyName;
    private Integer numberOfBudgetPlaces;
    private Integer numberOfContractPlaces;
    private Integer sumOfPlaces;

    public SpecialtyReportDto(Long id, String name,
                              Integer number,
                              Long facultyId,
                              String facultyName,
                              Integer numberOfBudgetPlaces,
                              Integer numberOfContractPlaces,
                              Integer sumOfPlaces) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.facultyId = facultyId;
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

    public Long getFacultyId() {return facultyId;}
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
