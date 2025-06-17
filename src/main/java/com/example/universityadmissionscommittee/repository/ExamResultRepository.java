package com.example.universityadmissionscommittee.repository;

import com.example.universityadmissionscommittee.data.ExamResult;
import com.example.universityadmissionscommittee.dto.ExamRowDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExamResultRepository extends JpaRepository<ExamResult, Long> {

    @Query("""
        select new com.example.universityadmissionscommittee.dto.ExamRowDto(
            a.id, a.firstName, a.lastName, a.phoneNumber,  a.email, sp.name, s.name, e.result
        )
        from ExamResult e
        join e.applicant a
        join e.subject s
        join a.specialty sp
    """)
    List<ExamRowDto> examRowData();



}
