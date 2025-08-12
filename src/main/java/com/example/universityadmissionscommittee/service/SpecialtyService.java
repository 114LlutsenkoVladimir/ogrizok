package com.example.universityadmissionscommittee.service;


import com.example.universityadmissionscommittee.data.Faculty;
import com.example.universityadmissionscommittee.data.Specialty;
import com.example.universityadmissionscommittee.data.Subject;
import com.example.universityadmissionscommittee.data.enums.SpecialtyType;
import com.example.universityadmissionscommittee.dto.specialty.SpecialtyCreateDto;
import com.example.universityadmissionscommittee.dto.specialty.SpecialtyIdAndNameDto;
import com.example.universityadmissionscommittee.dto.specialty.SpecialtyReportDto;
import com.example.universityadmissionscommittee.dto.specialty.SpecialtyReportGrouped;
import com.example.universityadmissionscommittee.exception.SpecialtyNotFoundException;
import com.example.universityadmissionscommittee.repository.specialty.SpecialtyRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SpecialtyService  extends AbstractCrudService<Specialty, Long, SpecialtyRepository>{

    private FacultyService facultyService;

    protected SpecialtyService(SpecialtyRepository repository,
                               FacultyService facultyService) {
        super(repository);
        this.facultyService = facultyService;
    }


    public Specialty findById(Long id) {
        return repository.findById(id).orElseThrow(
                SpecialtyNotFoundException::new
        );
    }

    public void updateSpecialtyPlaces(Long id,
                                      Optional<Integer> budgetPlaces,
                                      Optional<Integer> contractPlaces) {
        Specialty specialty = findById(id);
        budgetPlaces.ifPresent(specialty::setNumberOfBudgetPlaces);
        contractPlaces.ifPresent(specialty::setNumberOfContractPlaces);
        repository.save(specialty);
    }

    public SpecialtyReportGrouped findSpecialtyReportDtoById(Long specialtyId, String name, Integer number) {
        Optional<SpecialtyReportDto> specialty =
                repository.findSpecialtyReportDtoByFilters(specialtyId, name, number);
        if(specialty.isEmpty())
            throw new SpecialtyNotFoundException();

        return new SpecialtyReportGrouped(new ArrayList<>(List.of(specialty.get())));
    }

    public SpecialtyReportGrouped getSpecialtiesByFaculties(List<Long> facultyIds) {
        List<SpecialtyReportDto> specialties = repository.specialtiesByFacultiesData(facultyIds);
        return new SpecialtyReportGrouped(specialties);
    }

    public SpecialtyReportGrouped getSpecialtiesByOneFaculty(Long facultyId) {
        return getSpecialtiesByFaculties(new ArrayList<>(List.of(facultyId)));
    }

    public SpecialtyReportGrouped getSpecialtiesByFacultiesReport() {
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

    public Specialty createFromDto(SpecialtyCreateDto dto) {
        return save(new Specialty(dto.getName(), dto.getNumber(),
                facultyService.findById(dto.getFacultyId()),
                dto.getBudgetPlaces(), dto.getContractPlaces()));
    }
}
