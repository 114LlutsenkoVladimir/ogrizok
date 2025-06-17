package com.example.universityadmissionscommittee.data.enums;

public enum ApplicantStatus {
    ACCEPTED_CONTRACT("Прийнятий на контракт"),
    ACCEPTED_BUDGET("Прийнятий на бюджет"),
    PENDING("Очікує рішення"),
    REJECTED("Не прийнятий");

    private String name;

    ApplicantStatus(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
