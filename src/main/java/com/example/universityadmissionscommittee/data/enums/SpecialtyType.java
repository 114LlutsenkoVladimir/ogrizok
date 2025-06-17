package com.example.universityadmissionscommittee.data.enums;

import static com.example.universityadmissionscommittee.data.enums.FacultyType.*;

public enum SpecialtyType {
    COMPUTER_SCIENCE("Комп'ютерні науки", COMPUTER_SCIENCE_FACULTY),
    CYBERSECURITY("Кібербезпека", COMPUTER_SCIENCE_FACULTY),
    LAW("Правознавство", LAW_FACULTY),
    INTERNATIONAL_LAW("Міжнародне право", LAW_FACULTY),
    MEDICINE("Медицина", MEDICAL_FACULTY),
    PHARMACY("Фармація", MEDICAL_FACULTY);

    private String name;

    private FacultyType faculty;

    SpecialtyType(String name, FacultyType faculty) {
        this.name = name;
        this.faculty = faculty;
    }

    public FacultyType getFaculty() {
        return faculty;
    }

    @Override
    public String toString() {
        return name;
    }
}
