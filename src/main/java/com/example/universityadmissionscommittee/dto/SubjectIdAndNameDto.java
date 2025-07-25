package com.example.universityadmissionscommittee.dto;

public class SubjectIdAndNameDto {

    private String name;

    private Long id;

    public SubjectIdAndNameDto(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
