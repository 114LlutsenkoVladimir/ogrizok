package com.example.universityadmissionscommittee.data.enums;

public enum SubjectType {
    UKRAINIAN_LANGUAGE("Українська мова"),
    MATHEMATICS("Математика"),
    HISTORY_OF_UKRAINE("Історія України"),
    ENGLISH("Англійська мова"),
    BIOLOGY("Біологія"),
    CHEMISTRY("Хімія"),
    PHYSICS("Фізика"),
    GEOGRAPHY("Географія");

    private String name;

    SubjectType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
