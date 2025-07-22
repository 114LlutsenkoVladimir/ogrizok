package com.example.universityadmissionscommittee.data;

import com.example.universityadmissionscommittee.data.enums.SpecialtyType;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "specialty")
public class Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "name")
    String name;
    @Column(name = "number")
    int number;
    @ManyToOne
    @JoinColumn(name = "faculty_id")
    Faculty faculty;
    @Column(name = "number_of_budget_places")
    int numberOfBudgetPlaces;
    @Column(name = "number_of_contract_places")
    int numberOfContractPlaces;

    @ManyToMany
    @JoinTable(
            name = "subject_for_specialty",
            joinColumns = @JoinColumn(name = "specialty_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set<Subject> neededSubjects = new HashSet<>();


    @ManyToMany(mappedBy = "specialties")
    private Set<Applicant> applicants = new HashSet<>();



    public Specialty(String name,
                     int number, Faculty faculty,
                     int numberOfBudgetPlaces,
                     int numberOfContractPlaces) {
        this.name = name;
        this.number = number;
        this.faculty = faculty;
        this.numberOfBudgetPlaces = numberOfBudgetPlaces;
        this.numberOfContractPlaces = numberOfContractPlaces;
    }

    protected Specialty() {}

    public void linkSubject(Subject subject) {
        neededSubjects.add(subject);
        subject.addSpecialty(this);
    }

    public Set<Subject> getNeededSubjects() {
        return neededSubjects;
    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public int getNumberOfBudgetPlaces() {
        return numberOfBudgetPlaces;
    }

    public void setNumberOfBudgetPlaces(int numberOfBudgetPlaces) {
        this.numberOfBudgetPlaces = numberOfBudgetPlaces;
    }

    public int getNumberOfContractPlaces() {
        return numberOfContractPlaces;
    }

    public void setNumberOfContractPlaces(int numberOfContractPlaces) {
        this.numberOfContractPlaces = numberOfContractPlaces;
    }

    public void setNeededSubjects(Set<Subject> neededSubjects) {
        this.neededSubjects = neededSubjects;
    }



    public Set<Applicant> getApplicants() {
        return applicants;
    }

    public List<Applicant> getSortedApplicantList() {
        return applicants.stream()
                .sorted(Comparator.comparing(Applicant::calculateAverageScore).reversed())
                .collect(Collectors.toList());
    }

}
