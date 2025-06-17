package com.example.universityadmissionscommittee.service;

import com.example.universityadmissionscommittee.data.Subject;
import com.example.universityadmissionscommittee.data.enums.SubjectType;
import com.example.universityadmissionscommittee.repository.SubjectRepository;
import org.springframework.stereotype.Service;

@Service
public class SubjectService  extends AbstractCrudService<Subject, Long, SubjectRepository>{
    protected SubjectService(SubjectRepository repository) {
        super(repository);
    }

    public Subject findBySubjectType(SubjectType subjectType) {
        return repository.findBySubjectType(subjectType).orElseThrow(
                () -> new IllegalStateException("subject not found")
        );
    }

}
