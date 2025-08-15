package com.example.universityadmissionscommittee.data;



import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.*;

@Entity
@Table(
        name = "subject",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_subject_name", columnNames = "name")
        },
        indexes = {
                @Index(name = "idx_subject_name", columnList = "name")
        }
)
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 255)
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    /** Обратная сторона M:N. Владелец — Specialty (там @JoinTable). */
    @ManyToMany(mappedBy = "neededSubjects", fetch = FetchType.LAZY)
    private final Set<Specialty> specialties = new HashSet<>();

    /** Обратная сторона 1:N. Владелец FK — ExamResult. */
    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY)
    private final Set<ExamResult> examResults = new HashSet<>();

    protected Subject() { /* JPA */ }

    public Subject(String name) {
        this.name = Objects.requireNonNull(name, "name");
    }

    /* ===== Хелперы для поддержания двусторонних связей (package-private) =====
       Вызываются из Specialty.add/removeSubject и ExamResult.linkSubject.
       Не делаем их public, чтобы не нарушать инварианты с «владельческой» стороны.
     */

    void _addSpecialty(Specialty specialty) {
        specialties.add(Objects.requireNonNull(specialty, "specialty"));
    }

    void _removeSpecialty(Specialty specialty) {
        specialties.remove(Objects.requireNonNull(specialty, "specialty"));
    }

    void _addExamResult(ExamResult examResult) {
        examResults.add(Objects.requireNonNull(examResult, "examResult"));
    }

    void _removeExamResult(ExamResult examResult) {
        examResults.remove(Objects.requireNonNull(examResult, "examResult"));
    }

    /* ===== Getters / Setters ===== */

    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = Objects.requireNonNull(name, "name"); }

    public Set<Specialty> getSpecialties() {
        return Collections.unmodifiableSet(specialties);
    }

    public Set<ExamResult> getExamResults() {
        return Collections.unmodifiableSet(examResults);
    }

    /* ===== equals/hashCode/toString ===== */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subject other)) return false;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() { return getClass().hashCode(); }

    @Override
    public String toString() {
        return "Subject{id=" + id + ", name='" + name + "'}";
    }
}

