package com.example.universityadmissionscommittee.repository.specialty;

import com.example.universityadmissionscommittee.data.Specialty;
import com.example.universityadmissionscommittee.dto.specialty.SpecialtyIdAndNameDto;
import com.example.universityadmissionscommittee.dto.specialty.SpecialtyReportDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {

    @Query("""
        SELECT new com.example.universityadmissionscommittee.dto.specialty.SpecialtyReportDto(
            s.id, s.name, s.number, f.id, f.name,
            s.numberOfBudgetPlaces, s.numberOfContractPlaces,
            s.numberOfBudgetPlaces + s.numberOfContractPlaces
        )
        FROM Specialty s
        JOIN s.faculty f
        WHERE f.id IN :facultyIds
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
        WHERE (:id IS NULL OR s.id = :id)
          AND (:name IS NULL OR LOWER(s.name) = LOWER(:name))
          AND (:number IS NULL OR s.number = :number)
    """)
    Optional<SpecialtyReportDto> findSpecialtyReportDtoByFilters(
            @Param("id") Long id,
            @Param("name") String name,
            @Param("number") Integer number
    );

    @Query("""
        SELECT new com.example.universityadmissionscommittee.dto.specialty.SpecialtyIdAndNameDto(sp.id, sp.name)
        FROM Specialty sp
        WHERE NOT EXISTS (
          SELECT s
          FROM Subject s
          WHERE s MEMBER OF sp.neededSubjects
            AND s.id NOT IN :subjectIds
        )
        """)
    List<SpecialtyIdAndNameDto> findAllCoveredBySubjectsNative(@Param("subjectIds") List<Long> subjectIds);

    boolean existsByName(String name);
    boolean existsByNumber(Integer number);
}
