package com.example.universityadmissionscommittee.data;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(
        name = "specialty",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_specialty_name", columnNames = "name"),
                @UniqueConstraint(name = "uq_specialty_number", columnNames = "number")
        }
)
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 255)
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "number", nullable = false)
    private int number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty faculty;

    @Column(name = "number_of_budget_places", nullable = false)
    private int numberOfBudgetPlaces;

    @Column(name = "number_of_contract_places", nullable = false)
    private int numberOfContractPlaces;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "subject_for_specialty",
            joinColumns = @JoinColumn(name = "specialty_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set<Subject> neededSubjects = new HashSet<>();

    @OneToMany(mappedBy = "specialty", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<SpecialtyForApplicant> applicants = new HashSet<>();

    protected Specialty() {
    }

    public Specialty(String name, int number, Faculty faculty,
                     int numberOfBudgetPlaces, int numberOfContractPlaces) {
        this.name = Objects.requireNonNull(name, "name");
        this.number = number;
        this.faculty = Objects.requireNonNull(faculty, "faculty");
        this.numberOfBudgetPlaces = numberOfBudgetPlaces;
        this.numberOfContractPlaces = numberOfContractPlaces;
    }



    public void addSubject(Subject subject) {
        Objects.requireNonNull(subject, "subject");
        neededSubjects.add(subject);
        subject._addSpecialty(this);
    }

    public void removeSubject(Subject subject) {
        Objects.requireNonNull(subject, "subject");
        neededSubjects.remove(subject);
        subject._removeSpecialty(this);
    }

    public boolean hasNoApplicants() {
        return applicants.isEmpty();
    }



    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) {
        this.name = Objects.requireNonNull(name, "name");
    }

    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }

    public Faculty getFaculty() { return faculty; }
    public void setFaculty(Faculty faculty) {
        this.faculty = Objects.requireNonNull(faculty, "faculty");
    }

    public int getNumberOfBudgetPlaces() { return numberOfBudgetPlaces; }
    public void setNumberOfBudgetPlaces(int numberOfBudgetPlaces) {
        this.numberOfBudgetPlaces = numberOfBudgetPlaces;
    }

    public int getNumberOfContractPlaces() { return numberOfContractPlaces; }
    public void setNumberOfContractPlaces(int numberOfContractPlaces) {
        this.numberOfContractPlaces = numberOfContractPlaces;
    }

    public Set<Subject> getNeededSubjects() {
        return Collections.unmodifiableSet(neededSubjects);
    }

    public Set<SpecialtyForApplicant> getApplicants() {
        return Collections.unmodifiableSet(applicants);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Specialty other)) return false;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() { return getClass().hashCode(); }

    @Override
    public String toString() {
        return "Specialty{id=" + id + ", name='" + name + "', number=" + number + "}";
    }
}

