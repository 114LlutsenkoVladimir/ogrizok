package com.example.universityadmissionscommittee.data.enums;

import lombok.Getter;

public enum BenefitType {
    CHILDREN_OF_COMBATANTS("Дитина учасника бойових дій", 20),
    OLYMPIAD_WINNER("Переможець олімпіади", 15),
    PERSON_WITH_A_DISABILITY("Особа з інвалідністю", 20);
    private String name;

    @Getter
    private int additionalPoints;
    BenefitType(String name, int additionalPoints) {
        this.name = name;
        this.additionalPoints = additionalPoints;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getAdditionalPoints() {
        return additionalPoints;
    }
}
