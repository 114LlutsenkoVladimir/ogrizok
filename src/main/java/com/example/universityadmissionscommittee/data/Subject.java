package com.example.universityadmissionscommittee.data;


import com.example.universityadmissionscommittee.data.enums.SpecialtyType;
import com.example.universityadmissionscommittee.data.enums.SubjectType;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @ManyToMany(mappedBy = "neededSubjects")
    Set<Specialty> specialties = new HashSet<>();

    @OneToMany(mappedBy = "subject")
    Set<ExamResult> examResults = new HashSet<>();
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    SubjectType subjectType;



    protected Subject() {}
    public Subject(SubjectType subjectType) {
        this.subjectType = subjectType;
        name = subjectType.toString();
    }

    public void addExamResult(ExamResult examResult) {
        examResults.add(examResult);
    }

    public void addSpecialty(Specialty specialty) {
        specialties.add(specialty);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Specialty> getSpecialties() {
        return specialties;
    }

    public Set<ExamResult> getExamResults() {
        return examResults;
    }

    public SubjectType getSubjectType() {
        return subjectType;
    }



}
