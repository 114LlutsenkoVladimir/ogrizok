package com.example.universityadmissionscommittee.repository.examResult;

import com.example.universityadmissionscommittee.data.ExamResult;
import com.example.universityadmissionscommittee.dto.ExamRowDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExamResultRepository extends JpaRepository<ExamResult, Long>, ExamResultRepositoryCustom {

    @Query("""
        SELECT new com.example.universityadmissionscommittee.dto.ExamRowDto(
                a.id,
                a.firstName,
                a.lastName,
                a.phoneNumber,
                a.email,
                sp.id,
                sp.name,
                s.id,
                s.name,
                e.result
            )
            from ExamResult e
            join e.applicant a
            join e.subject s
            join a.specialties sp
            WHERE a.id = :applicantId
    """)
    List<ExamRowDto> findExamRowsByApplicantId(@Param("applicantId") Long id);
}
