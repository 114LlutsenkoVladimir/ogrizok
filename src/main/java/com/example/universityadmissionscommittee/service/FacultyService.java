package com.example.universityadmissionscommittee.service;


import com.example.universityadmissionscommittee.data.Faculty;
import com.example.universityadmissionscommittee.data.Specialty;
import com.example.universityadmissionscommittee.data.enums.FacultyType;
import com.example.universityadmissionscommittee.dto.FacultyIdAndNameDto;
import com.example.universityadmissionscommittee.dto.specialty.SpecialtyIdAndNameDto;
import com.example.universityadmissionscommittee.repository.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyService extends AbstractCrudService<Faculty, Long, FacultyRepository>{
    protected FacultyService(FacultyRepository repository) {
        super(repository);
    }

    public List<FacultyIdAndNameDto> toIdAndNameDto(List<Faculty> faculties) {
        return faculties.stream()
                .map(f -> new FacultyIdAndNameDto(f.getId(), f.getName())).toList();
    }

    public List<FacultyIdAndNameDto> allIdAndName() {
        return toIdAndNameDto(findAll());
    }
}
