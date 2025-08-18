package com.example.universityadmissionscommittee.service;

import com.example.universityadmissionscommittee.data.Subject;
import com.example.universityadmissionscommittee.data.enums.SubjectType;
import com.example.universityadmissionscommittee.dto.SubjectIdAndNameDto;
import com.example.universityadmissionscommittee.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService  extends AbstractCrudService<Subject, Long, SubjectRepository>{
    protected SubjectService(SubjectRepository repository) {
        super(repository);
    }


    public List<SubjectIdAndNameDto> toIdAndNameDto(List<Subject> subjects) {
        return subjects.stream()
                .map(s -> new SubjectIdAndNameDto(s.getName(), s.getId())).toList();
    }

    public List<SubjectIdAndNameDto> allIdAndName() {
        return toIdAndNameDto(findAll());
    }

}
