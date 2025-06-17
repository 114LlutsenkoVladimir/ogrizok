package com.example.universityadmissionscommittee.repository;

import com.example.universityadmissionscommittee.data.Faculty;
import com.example.universityadmissionscommittee.data.enums.FacultyType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Optional<Faculty> findByFacultyType(FacultyType facultyType);
}
