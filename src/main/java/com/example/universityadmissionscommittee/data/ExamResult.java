package com.example.universityadmissionscommittee.data;

import com.example.universityadmissionscommittee.dto.ExamRowDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

import java.util.Optional;


@Entity
@Table(name = "exam_result")

public class ExamResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    Applicant applicant;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    Subject subject;

    @Column(name = "result")
    Integer result;

    public ExamResult(Applicant applicant, Subject subject, int result) {
        this.applicant = applicant;
        this.subject = subject;
        this.result = result;
    }

    protected ExamResult() {
    }

    public ExamResult(int result) {
        this.result = result;
    }

    public void linkSubject(Subject subject) {
        this.subject = subject;
        subject.addExamResult(this);
    }


    public Subject getSubject() {
        return subject;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public int getResult() {
        return result;
    }
}
