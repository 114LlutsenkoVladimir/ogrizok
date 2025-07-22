package com.example.universityadmissionscommittee.service;


import com.example.universityadmissionscommittee.data.Faculty;
import com.example.universityadmissionscommittee.data.Specialty;
import com.example.universityadmissionscommittee.data.Subject;
import com.example.universityadmissionscommittee.data.enums.SpecialtyType;
import com.example.universityadmissionscommittee.dto.specialty.SpecialtyIdAndNameDto;
import com.example.universityadmissionscommittee.dto.specialty.SpecialtyReportDto;
import com.example.universityadmissionscommittee.repository.specialty.SpecialtyRepository;
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

    public Map<String, List<SpecialtyReportDto>> getSpecialtiesByFaculties(List<Long> facultyIds) {
        List<SpecialtyReportDto> specialties = repository.specialtiesByFacultiesData(facultyIds);
        return specialties.stream()
                .collect(Collectors.groupingBy(SpecialtyReportDto::getFacultyName));
    }

    public Map<String, List<SpecialtyReportDto>> getSpecialtiesByOneFaculty(Long facultyId) {
        return getSpecialtiesByFaculties(new ArrayList<>(List.of(facultyId)));
    }

    public Map<String, List<SpecialtyReportDto>> getSpecialtiesByFacultiesReport() {
        return getSpecialtiesByFaculties(repository.findAll().stream().map(Specialty::getId).toList());
    }

    public List<SpecialtyIdAndNameDto> findAvailableForSubjects(List<Long> subjectIds) {
        return findAll().stream()
                .filter(s -> s.getNeededSubjects().stream()
                        .map(Subject::getId)
                        .allMatch(subjectIds::contains))
                .map(s -> new SpecialtyIdAndNameDto(s.getId(), s.getName()))
                .toList();
    }

    public List<SpecialtyIdAndNameDto> toIdAndNameDto(List<Specialty> specialties) {
        return specialties.stream()
                .map(s -> new SpecialtyIdAndNameDto(s.getId(), s.getName())).toList();
    }

    public List<SpecialtyIdAndNameDto> allIdAndName() {
        return toIdAndNameDto(findAll());
    }
}
