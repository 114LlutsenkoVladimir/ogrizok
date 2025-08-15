package com.example.universityadmissionscommittee.data;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.*;

@Entity
@Table(
        name = "applicant",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_applicant_email", columnNames = "email"),
                @UniqueConstraint(name = "uq_applicant_phone", columnNames = "phone_number")
        },
        indexes = {
                @Index(name = "idx_applicant_email", columnList = "email"),
                @Index(name = "idx_applicant_phone", columnList = "phone_number")
        }
)

public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @NotBlank
    @Size(max = 100)
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @NotBlank
    @Email
    @Column(name = "email", nullable = false, length = 254)
    private String email;

    @NotBlank
    @Size(max = 32)
    @Column(name = "phone_number", nullable = false, length = 32)
    private String phoneNumber;

    @OneToMany(
            mappedBy = "applicant",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Set<ExamResult> examResults = new HashSet<>();


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "benefit_for_applicant",
            joinColumns = @JoinColumn(name = "applicant_id"),
            inverseJoinColumns = @JoinColumn(name = "benefit_id")
    )
    private Set<Benefit> benefits = new HashSet<>();

    @OneToMany(
            mappedBy = "applicant",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Set<SpecialtyForApplicant> specialties = new HashSet<>();

    protected Applicant() {}

    public Applicant(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = Objects.requireNonNull(firstName, "firstName");
        this.lastName = Objects.requireNonNull(lastName, "lastName");
        this.email = Objects.requireNonNull(email, "email");
        this.phoneNumber = Objects.requireNonNull(phoneNumber, "phoneNumber");
    }

    /* ==========================
       Bidirectional helpers
       ========================== */

    public void addExamResult(ExamResult examResult) {
        Objects.requireNonNull(examResult, "examResult");
        examResults.add(examResult);
        examResult.setApplicant(this);
    }

    public void removeExamResult(ExamResult examResult) {
        Objects.requireNonNull(examResult, "examResult");
        if (examResults.remove(examResult)) {
            examResult.setApplicant(null);
        }
    }

    public void addBenefit(Benefit benefit) {
        Objects.requireNonNull(benefit, "benefit");
        benefits.add(benefit);
        // если у Benefit есть обратная сторона applicants — поддержи и её:
        // benefit.getApplicants().add(this);
    }

    public void removeBenefit(Benefit benefit) {
        Objects.requireNonNull(benefit, "benefit");
        benefits.remove(benefit);
    }

    public void addBenefits(Collection<Benefit> toAdd) {
        Objects.requireNonNull(toAdd, "benefits");
        toAdd.forEach(this::addBenefit);
    }

    public void addSpecialty(SpecialtyForApplicant sfa) {
        Objects.requireNonNull(sfa, "specialtyForApplicant");
        specialties.add(sfa);
        sfa.setApplicant(this);
    }

    public void removeSpecialty(SpecialtyForApplicant sfa) {
        Objects.requireNonNull(sfa, "specialtyForApplicant");
        if (specialties.remove(sfa)) {
            sfa.setApplicant(null);
        }
    }

    /* ==========================
       Domain operations
       ========================== */

    /** Возвращает балл по предмету, иначе бросает IllegalStateException. */
    public int getResultBySubjectId(Long subjectId) {
        Objects.requireNonNull(subjectId, "subjectId");
        return examResults.stream()
                .filter(er -> {
                    var subject = er.getSubject();
                    return subject != null && subjectId.equals(subject.getId());
                })
                .mapToInt(ExamResult::getResult)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Exam result not found for subjectId=" + subjectId));
    }

    /** Найти оценку по предмету как Optional (иногда удобнее на уровне сервиса). */
    public Optional<ExamResult> findExamResultBySubjectId(Long subjectId) {
        Objects.requireNonNull(subjectId, "subjectId");
        return examResults.stream()
                .filter(er -> {
                    var subject = er.getSubject();
                    return subject != null && subjectId.equals(subject.getId());
                })
                .findFirst();
    }

    /* ==========================
       Getters / Setters
       ========================== */

    public Long getId() { return id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) {
        this.firstName = Objects.requireNonNull(firstName, "firstName");
    }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) {
        this.lastName = Objects.requireNonNull(lastName, "lastName");
    }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        this.email = Objects.requireNonNull(email, "email");
    }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = Objects.requireNonNull(phoneNumber, "phoneNumber");
    }

    /** Возвращаю немодифицируемое представление, чтобы не ломали инварианты. */
    public Set<ExamResult> getExamResults() {
        return Collections.unmodifiableSet(examResults);
    }

    /** Управляй льготами через add/remove, чтобы исключить null и дубли. */
    public Set<Benefit> getBenefits() {
        return Collections.unmodifiableSet(benefits);
    }

    public Set<SpecialtyForApplicant> getSpecialties() {
        return Collections.unmodifiableSet(specialties);
    }

    /* ==========================
       equals / hashCode / toString
       ========================== */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Applicant other)) return false;
        // entity equality by id (Hibernate-friendly): равны только если оба id заданы и равны
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        // рекомендуемый подход для JPA сущностей: стабилен и не тянет коллекции
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Applicant{id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

