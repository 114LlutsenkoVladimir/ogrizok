package com.example.universityadmissionscommittee.data;

import com.example.universityadmissionscommittee.data.enums.ApplicantStatus;
import com.example.universityadmissionscommittee.dto.specialty.SpecialtyForApplicantId;
import jakarta.persistence.*;

@Entity
@Table(name="specialty_for_apllicant")

public class SpecialtyForApplicant {
    @EmbeddedId
    private SpecialtyForApplicantId id;

    @ManyToOne
    @JoinColumn(name = "specialty_id", insertable = false, updatable = false)
    private Specialty specialty;
    @ManyToOne
    @JoinColumn(name = "applicant_id", insertable = false, updatable = false)
    private Applicant applicant;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "applicant_status")
    @Convert(converter = ApplicantStatusConverter.class)
    private ApplicantStatus applicantStatus = ApplicantStatus.PENDING;

    protected SpecialtyForApplicant() {}

    public SpecialtyForApplicant(Specialty specialty,
                                 Integer priority) {
        this.specialty = specialty;
        this.priority = priority;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public ApplicantStatus getApplicantStatus() {
        return applicantStatus;
    }

    public void setApplicantStatus(ApplicantStatus applicantStatus) {
        this.applicantStatus = applicantStatus;
    }
}
