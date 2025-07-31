package com.example.universityadmissionscommittee.repository.examResult;

import com.example.universityadmissionscommittee.dto.ExamRowDto;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExamResultRepositoryImpl implements ExamResultRepositoryCustom {
    private final EntityManager entityManager;

    public ExamResultRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<ExamRowDto> examRowData(List<Long> specialtyIds) {
        String sql = """
        SELECT 
            a.id AS applicant_id,
            a.first_name,
            a.last_name,
            a.phone_number,
            a.email,
            sp.id AS specialty_id,
            sp.name AS specialty_name,
            s.id AS subject_id,
            s.name AS subject_name,
            e.result,
            asp.priority,
            asp.applicant_status,
            b.id as benefit_id,
            b.name as benefit_name,
            b.additional_points as benefit_points
        FROM specialty sp
        JOIN subject_for_specialty sfs ON sfs.specialty_id = sp.id
        JOIN subject s ON s.id = sfs.subject_id
    
        LEFT JOIN specialty_for_applicant asp ON asp.specialty_id = sp.id
        LEFT JOIN applicant a ON a.id = asp.applicant_id
        LEFT JOIN exam_result e ON e.applicant_id = a.id AND e.subject_id = s.id
        LEFT JOIN benefit_for_applicant bfa ON a.id = bfa.applicant_id
        LEFT JOIN benefit b ON b.id = bfa.benefit_id
    
        WHERE sp.id IN (?1)
        ORDER BY sp.id, a.id, s.id
    """;
        return entityManager
                .createNativeQuery(sql, "ExamRowDtoMapping")
                .setParameter(1, specialtyIds)
                .getResultList();
    }
}

