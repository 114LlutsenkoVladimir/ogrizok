package com.example.universityadmissionscommittee.dto.specialty;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SpecialtyForApplicantId implements Serializable {

    @Column(name = "applicant_id")
    private Long applicantId;

    @Column(name = "specialty_id")
    private Long specialtyId;

    public SpecialtyForApplicantId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecialtyForApplicantId that = (SpecialtyForApplicantId) o;
        return Objects.equals(applicantId, that.applicantId) && Objects.equals(specialtyId, that.specialtyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicantId, specialtyId);
    }
}
