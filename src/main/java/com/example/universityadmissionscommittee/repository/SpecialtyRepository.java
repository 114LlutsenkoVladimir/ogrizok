package com.example.universityadmissionscommittee.repository;

import com.example.universityadmissionscommittee.data.Specialty;
import com.example.universityadmissionscommittee.data.enums.SpecialtyType;
import com.example.universityadmissionscommittee.dto.SpecialtyReportDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {

    Optional<Specialty> findBySpecialtyType(SpecialtyType specialtyType);

    Optional<Specialty> findByName(String name);

    @Query("""
        SELECT new com.example.universityadmissionscommittee.dto.SpecialtyReportDto(
            s.id, s.name, s.number, f.name,
            s.numberOfBudgetPlaces, s.numberOfContractPlaces,
            s.numberOfBudgetPlaces + s.numberOfContractPlaces
        )
        FROM Specialty s
        JOIN s.faculty f
        WHERE f.name IN : facultyNames
    """)
    List<SpecialtyReportDto> specialtiesByFacultiesData(@Param("facultyNames") List<String> facultyNames);
}
