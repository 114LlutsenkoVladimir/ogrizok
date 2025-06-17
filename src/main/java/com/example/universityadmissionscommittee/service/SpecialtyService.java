package com.example.universityadmissionscommittee.service;


import com.example.universityadmissionscommittee.data.Faculty;
import com.example.universityadmissionscommittee.data.Specialty;
import com.example.universityadmissionscommittee.data.enums.SpecialtyType;
import com.example.universityadmissionscommittee.dto.SpecialtyReportDto;
import com.example.universityadmissionscommittee.repository.SpecialtyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SpecialtyService  extends AbstractCrudService<Specialty, Long, SpecialtyRepository>{
    protected SpecialtyService(SpecialtyRepository repository) {
        super(repository);
    }

    public Specialty findBySpecialtyType(SpecialtyType specialtyType) {
        return repository.findBySpecialtyType(specialtyType).orElseThrow(
                () -> new IllegalStateException("specialty not found"));
    }

    public List<Specialty> filterByFaculty(List<Specialty> specialties, Faculty faculty) {
        return specialties.stream()
                .filter(s -> s.getFaculty().equals(faculty))
                .collect(Collectors.toList());
    }

    public Specialty findByName(String name) {
        return repository.findByName(name).orElseThrow(
                NoSuchElementException::new
        );
    }
    public Specialty findById(Long id) {
        return repository.findById(id).orElseThrow(
                NoSuchElementException::new
        );
    }

    public List<SpecialtyReportDto> specialtiesByFacultiesData(List<String> facultyNames) {
        return repository.specialtiesByFacultiesData(facultyNames);
    }
}
