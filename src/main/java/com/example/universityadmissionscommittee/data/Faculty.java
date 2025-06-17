package com.example.universityadmissionscommittee.data;

import com.example.universityadmissionscommittee.data.enums.FacultyType;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "faculty")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Specialty> specialties = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    FacultyType facultyType;

    public Faculty(FacultyType facultyType) {
        this.facultyType = facultyType;
        name = facultyType.toString();
    }

    protected Faculty() {}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return Objects.equals(id, faculty.id) && facultyType == faculty.facultyType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, facultyType);
    }
}
