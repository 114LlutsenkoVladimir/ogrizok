package com.example.universityadmissionscommittee.repository.examResult;

import com.example.universityadmissionscommittee.data.ExamResult;
import com.example.universityadmissionscommittee.dto.ExamRowDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

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
                e.result,
                spf.priority,
                spf.applicantStatus,
                b.id,
                b.name,
                b.additionalPoints
            )
            from ExamResult e
            join e.applicant a
            join e.subject s
            join a.specialties spf
            join spf.specialty sp
            LEFT join a.benefits b
            WHERE (:applicantId is null or a.id = :applicantId)
            AND (:email IS NULL OR a.email = :email)
            AND (:phoneNumber IS NULL OR a.phoneNumber = :phoneNumber)
    """)
    List<ExamRowDto> findExamRowsByApplicantKeyAttributes(@Param("applicantId") Long id,
                                                              @Param("email") String email,
                                                              @Param("phoneNumber") String phoneNumber);
}
