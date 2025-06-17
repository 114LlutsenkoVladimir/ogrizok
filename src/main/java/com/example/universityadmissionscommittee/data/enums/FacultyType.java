package com.example.universityadmissionscommittee.data.enums;

public enum FacultyType {
    COMPUTER_SCIENCE_FACULTY("Факультет комп'ютерних наук"),
    LAW_FACULTY("Юридичний факультет"),
    MEDICAL_FACULTY("Медичний факультет");

    private String name;

    FacultyType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
