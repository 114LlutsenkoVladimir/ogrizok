package com.example.universityadmissionscommittee.service;

import com.example.universityadmissionscommittee.data.ExamResult;
import com.example.universityadmissionscommittee.dto.ExamRowDto;
import com.example.universityadmissionscommittee.repository.examResult.ExamResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamResultService  extends AbstractCrudService<ExamResult, Long, ExamResultRepository>{
    protected ExamResultService(ExamResultRepository repository) {
        super(repository);
    }

    public List<ExamRowDto> getExamRowData(List<Long> specialtyIds) {return repository.examRowData(specialtyIds); }

}
