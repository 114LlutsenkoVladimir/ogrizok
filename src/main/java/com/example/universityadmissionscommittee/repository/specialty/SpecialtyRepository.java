package com.example.universityadmissionscommittee.repository.specialty;

import com.example.universityadmissionscommittee.data.Specialty;
import com.example.universityadmissionscommittee.dto.specialty.SpecialtyReportDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {

    @Query("""
        SELECT new com.example.universityadmissionscommittee.dto.specialty.SpecialtyReportDto(
            s.id, s.name, s.number, f.id, f.name,
            s.numberOfBudgetPlaces, s.numberOfContractPlaces,
            s.numberOfBudgetPlaces + s.numberOfContractPlaces
        )
        FROM Specialty s
        JOIN s.faculty f
        WHERE f.id IN : facultyIds
    """)
    List<SpecialtyReportDto> specialtiesByFacultiesData(@Param("facultyIds") List<Long> facultyIds);


    @Query("""
        SELECT new com.example.universityadmissionscommittee.dto.specialty.SpecialtyReportDto(
            s.id, s.name, s.number, f.id, f.name,
            s.numberOfBudgetPlaces, s.numberOfContractPlaces,
            s.numberOfBudgetPlaces + s.numberOfContractPlaces
        )
        FROM Specialty s
        JOIN s.faculty f
        WHERE s.id = :specialtyId
    """)
    SpecialtyReportDto findSpecialtyReportDtoById(@Param("specialtyId") Long specialtyId);
}
