package com.example.universityadmissionscommittee.data.enums;

public enum ApplicantStatus {
    ACCEPTED_CONTRACT("Прийнято на контракт"),
    ACCEPTED_BUDGET("Прийнято на бюджет"),
    PENDING("Невизначено");

    private String name;

    ApplicantStatus(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
