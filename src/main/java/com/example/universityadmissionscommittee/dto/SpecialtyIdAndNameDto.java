package com.example.universityadmissionscommittee.dto;

public class SpecialtyIdAndNameDto {
    Long id;

    String name;

    public SpecialtyIdAndNameDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
