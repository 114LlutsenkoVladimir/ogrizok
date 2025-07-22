package com.example.universityadmissionscommittee.dto.specialty;

public class SpecialtyIdAndNameDto {
    private Long id;

    private String name;

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
