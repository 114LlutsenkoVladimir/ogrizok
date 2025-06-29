package com.example.universityadmissionscommittee.service;


import com.example.universityadmissionscommittee.data.Faculty;
import com.example.universityadmissionscommittee.data.Specialty;
import com.example.universityadmissionscommittee.data.Subject;
import com.example.universityadmissionscommittee.data.enums.SpecialtyType;
import com.example.universityadmissionscommittee.dto.SpecialtyIdAndNameDto;
import com.example.universityadmissionscommittee.dto.SpecialtyReportDto;
import com.example.universityadmissionscommittee.repository.SpecialtyRepository;
import org.springframework.stereotype.Service;

import java.util.*;
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

    public Map<String, List<SpecialtyReportDto>> getSpecialtiesByFaculties(List<String> facultyNames) {
        List<SpecialtyReportDto> specialties = repository.specialtiesByFacultiesData(facultyNames);
        return specialties.stream()
                .collect(Collectors.groupingBy(SpecialtyReportDto::getFacultyName));
    }

    public Map<String, List<SpecialtyReportDto>> getSpecialtiesByOneFaculty(String facultyName) {
        return getSpecialtiesByFaculties(new ArrayList<>(List.of(facultyName)));
    }

    public Map<String, List<SpecialtyReportDto>> getSpecialtiesByFacultiesReport() {
        return getSpecialtiesByFaculties(repository.findAll().stream().map(Specialty::getName).toList());
    }

    public List<SpecialtyIdAndNameDto> findAvailableForSubjects(List<Long> subjectIds) {
        return findAll().stream()
                .filter(s -> s.getNeededSubjects().stream()
                        .map(Subject::getId)
                        .allMatch(subjectIds::contains))
                .map(s -> new SpecialtyIdAndNameDto(s.getId(), s.getName()))
                .toList();
    }
}
