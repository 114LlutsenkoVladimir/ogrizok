package com.example.universityadmissionscommittee.service;


import com.example.universityadmissionscommittee.data.Faculty;
import com.example.universityadmissionscommittee.data.enums.FacultyType;
import com.example.universityadmissionscommittee.repository.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FacultyService extends AbstractCrudService<Faculty, Long, FacultyRepository>{
    protected FacultyService(FacultyRepository repository) {
        super(repository);
    }

    public Faculty findByFacultyType(FacultyType facultyType) {
        return repository.findByFacultyType(facultyType)
                .orElseThrow( () -> new IllegalStateException("dfs"));
    }
}
