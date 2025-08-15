package com.example.universityadmissionscommittee.data;


import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.*;

@Entity
@Table(
        name = "faculty",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_faculty_name", columnNames = "name")
        }
)
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 255)
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @OneToMany(
            mappedBy = "faculty",
            cascade = { CascadeType.PERSIST, CascadeType.MERGE },
            orphanRemoval = false,
            fetch = FetchType.LAZY
    )
    private Set<Specialty> specialties = new HashSet<>();

    protected Faculty() {}

    public Faculty(String name) {
        this.name = Objects.requireNonNull(name, "name");
    }


    public void addSpecialty(Specialty specialty) {
        Objects.requireNonNull(specialty, "specialty");
        specialties.add(specialty);
        specialty.setFaculty(this);
    }

    public void removeSpecialty(Specialty specialty) {
        Objects.requireNonNull(specialty, "specialty");
        if (specialties.remove(specialty)) {
            specialty.setFaculty(null);
        }
    }


    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) {
        this.name = Objects.requireNonNull(name, "name");
    }

    public Set<Specialty> getSpecialties() {
        return Collections.unmodifiableSet(specialties);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Faculty other)) return false;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() { return getClass().hashCode(); }

    @Override
    public String toString() {
        return "Faculty{id=" + id + ", name='" + name + "'}";
    }
}

