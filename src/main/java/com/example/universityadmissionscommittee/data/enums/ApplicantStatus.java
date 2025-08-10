package com.example.universityadmissionscommittee.data.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ApplicantStatus {
    ACCEPTED_CONTRACT("Прийнято на контракт"),
    ACCEPTED_BUDGET("Прийнято на бюджет"),
    PENDING("Невизначено");

    private String name;

    ApplicantStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getCode() { return name(); }
    @Override
    public String toString() {
        return name;
    }
}
