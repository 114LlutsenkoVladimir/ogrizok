package com.example.universityadmissionscommittee.dto.specialty;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SpecialtyForApplicantId implements Serializable {
    @EmbeddedId
    private SpecialtyForApplicantId id;
    private Long applicantId;
    private Long specialtyId;

    public SpecialtyForApplicantId(Long applicantId, Long specialtyId) {
        this.applicantId = applicantId;
        this.specialtyId = specialtyId;
    }

    public SpecialtyForApplicantId() {
    }

    public Long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Long applicantId) {
        this.applicantId = applicantId;
    }

    public Long getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(Long specialtyId) {
        this.specialtyId = specialtyId;
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
