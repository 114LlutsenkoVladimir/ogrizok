package com.example.universityadmissionscommittee.data;

import com.example.universityadmissionscommittee.data.enums.ApplicantStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(
        name = "specialty_for_applicant",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_sfa_pair", columnNames = {"applicant_id", "specialty_id"}),
                @UniqueConstraint(name = "uq_sfa_priority", columnNames = {"applicant_id", "priority"})
        }
)
public class SpecialtyForApplicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Владелец связи (есть FK). Делаем LAZY и обязательным. */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "specialty_id", nullable = false)
    private Specialty specialty;

    /** Владелец связи (есть FK). Делаем LAZY и обязательным. */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "applicant_id", nullable = false)
    private Applicant applicant;

    @NotNull
    @Min(1)
    @Column(name = "priority", nullable = false)
    private Integer priority;

    /** В БД хранится строковое имя енама → используем EnumType.STRING. */
    @Enumerated(EnumType.STRING)
    @Column(name = "applicant_status", nullable = false, length = 32)
    private ApplicantStatus applicantStatus = ApplicantStatus.PENDING;

    protected SpecialtyForApplicant() {
        // JPA only
    }

    public SpecialtyForApplicant(Applicant applicant, Specialty specialty, Integer priority) {
        this.applicant = Objects.requireNonNull(applicant, "applicant");
        this.specialty = Objects.requireNonNull(specialty, "specialty");
        this.priority = Objects.requireNonNull(priority, "priority");
    }

    /** Удобная фабрика: создаёт связь и синхронизирует обе стороны. */
    public static SpecialtyForApplicant link(Applicant applicant, Specialty specialty, Integer priority) {
        SpecialtyForApplicant sfa = new SpecialtyForApplicant(applicant, specialty, priority);
        applicant.addSpecialty(sfa);     // двусторонняя синхронизация
        return sfa;
    }

    /* ===== Getters / Setters ===== */

    public Long getId() { return id; }

    public Specialty getSpecialty() { return specialty; }
    public void setSpecialty(Specialty specialty) {
        this.specialty = Objects.requireNonNull(specialty, "specialty");
    }

    public Applicant getApplicant() { return applicant; }
    public void setApplicant(Applicant applicant) {
        this.applicant = Objects.requireNonNull(applicant, "applicant");
    }

    public Integer getPriority() { return priority; }
    public void setPriority(Integer priority) {
        this.priority = Objects.requireNonNull(priority, "priority");
    }

    public ApplicantStatus getApplicantStatus() { return applicantStatus; }
    public void setApplicantStatus(ApplicantStatus applicantStatus) {
        this.applicantStatus = Objects.requireNonNull(applicantStatus, "applicantStatus");
    }

    /* ===== equals/hashCode/toString ===== */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpecialtyForApplicant other)) return false;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() { return getClass().hashCode(); }

    @Override
    public String toString() {
        return "SpecialtyForApplicant{id=" + id +
                ", applicantId=" + (applicant != null ? applicant.getId() : null) +
                ", specialtyId=" + (specialty != null ? specialty.getId() : null) +
                ", priority=" + priority +
                ", status=" + applicantStatus +
                "}";
    }
}

