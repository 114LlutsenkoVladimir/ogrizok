package com.example.universityadmissionscommittee.data;

import jakarta.persistence.*;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.util.Objects;

@Entity
@Table(
        name = "exam_result",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_exam_pair", columnNames = {"applicant_id", "subject_id"})
        },
        indexes = {
                @Index(name = "idx_exam_applicant", columnList = "applicant_id"),
                @Index(name = "idx_exam_subject",   columnList = "subject_id")
        }
)
public class ExamResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Обязательная ссылка, LAZY. Владелец FK — эта сущность. */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "applicant_id", nullable = false)
    private Applicant applicant;

    /** Обязательная ссылка, LAZY. Владелец FK — эта сущность. */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    /** Интегер обязателен → используем примитив и Bean Validation. */
    @Min(0)
    @Max(200) // поправь границы, если у тебя другие
    @Column(name = "result", nullable = false)
    private int result;

    protected ExamResult() {
        // JPA
    }

    public ExamResult(Applicant applicant, Subject subject, int result) {
        this.applicant = Objects.requireNonNull(applicant, "applicant");
        this.subject   = Objects.requireNonNull(subject, "subject");
        this.result    = result;
    }

    /** Удобная фабрика с двусторонней синхронизацией. */
    public static ExamResult link(Applicant applicant, Subject subject, int result) {
        ExamResult er = new ExamResult(applicant, subject, result);
        applicant.addExamResult(er); // выставит обратную сторону
        subject._addExamResult(er);   // если у Subject есть коллекция результатов
        return er;
    }

    /* ===== Helpers (двусторонняя синхронизация) ===== */

    public void linkApplicant(Applicant applicant) {
        this.applicant = Objects.requireNonNull(applicant, "applicant");
        applicant.addExamResult(this);
    }

    public void linkSubject(Subject subject) {
        this.subject = Objects.requireNonNull(subject, "subject");
        subject._addExamResult(this);
    }

    /* ===== Getters / Setters ===== */

    public Long getId() { return id; }

    public Applicant getApplicant() { return applicant; }
    public void setApplicant(Applicant applicant) {
        this.applicant = Objects.requireNonNull(applicant, "applicant");
    }

    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) {
        this.subject = Objects.requireNonNull(subject, "subject");
    }

    public int getResult() { return result; }
    public void setResult(int result) { this.result = result; }

    /* ===== equals/hashCode/toString ===== */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExamResult other)) return false;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() { return getClass().hashCode(); }

    @Override
    public String toString() {
        return "ExamResult{id=" + id +
                ", applicantId=" + (applicant != null ? applicant.getId() : null) +
                ", subjectId="   + (subject != null ? subject.getId() : null) +
                ", result=" + result + "}";
    }
}

