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


    public Faculty(String facultyName) {
        name = facultyName;
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



}
