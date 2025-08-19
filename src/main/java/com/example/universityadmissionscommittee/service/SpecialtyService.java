package com.example.universityadmissionscommittee.service;


import com.example.universityadmissionscommittee.data.Faculty;
import com.example.universityadmissionscommittee.data.Specialty;
import com.example.universityadmissionscommittee.data.Subject;
import com.example.universityadmissionscommittee.dto.specialty.SpecialtyCreateDto;
import com.example.universityadmissionscommittee.dto.specialty.SpecialtyIdAndNameDto;
import com.example.universityadmissionscommittee.dto.specialty.SpecialtyReportDto;
import com.example.universityadmissionscommittee.dto.specialty.SpecialtyReportGrouped;
import com.example.universityadmissionscommittee.exception.specialty.SpecialtyCreationException;
import com.example.universityadmissionscommittee.exception.specialty.SpecialtyDeletingException;
import com.example.universityadmissionscommittee.exception.specialty.SpecialtyNotFoundException;
import com.example.universityadmissionscommittee.repository.specialty.SpecialtyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    @Override
    public void deleteById(Long id) {
        Specialty specialty = findById(id);
        if(!specialty.getApplicants().isEmpty())
            throw new SpecialtyDeletingException("у спеціальності є абітурієнти");
        super.deleteById(id);
    }

    @Transactional
    public void updateSpecialtyPlaces(Long id,
                                      Optional<Integer> budgetPlaces,
                                      Optional<Integer> contractPlaces) {
        Specialty specialty = findById(id);
        budgetPlaces.ifPresent(specialty::setNumberOfBudgetPlaces);
        contractPlaces.ifPresent(specialty::setNumberOfContractPlaces);
        repository.save(specialty);
    }

    @Transactional(readOnly = true)
    public SpecialtyReportGrouped findSpecialtyReportDtoById(Long specialtyId, String name, Integer number) {
        Optional<SpecialtyReportDto> specialty =
                repository.findSpecialtyReportDtoByFilters(specialtyId, name, number);
        if(specialty.isEmpty())
            throw new SpecialtyNotFoundException();

        return new SpecialtyReportGrouped(new ArrayList<>(List.of(specialty.get())));
    }

    @Transactional(readOnly = true)
    public SpecialtyReportGrouped getSpecialtiesByFaculties(List<Long> facultyIds) {
        List<SpecialtyReportDto> specialties = repository.specialtiesByFacultiesData(facultyIds);
        return new SpecialtyReportGrouped(specialties);
    }

    @Transactional(readOnly = true)
    public SpecialtyReportGrouped getSpecialtiesByOneFaculty(Long facultyId) {
        return getSpecialtiesByFaculties(List.of(facultyId));
    }

    @Transactional(readOnly = true)
    public SpecialtyReportGrouped getSpecialtiesByFacultiesReport() {
        return getSpecialtiesByFaculties(facultyService.findAll().stream().map(Faculty::getId).toList());
    }

    @Transactional(readOnly = true)
    public List<SpecialtyIdAndNameDto> findAvailableForSubjects(List<Long> subjectIds) {
        return repository.findAllCoveredBySubjectsNative(subjectIds);
    }


    public List<SpecialtyIdAndNameDto> toIdAndNameDto(List<Specialty> specialties) {
        return specialties.stream()
                .map(s -> new SpecialtyIdAndNameDto(s.getId(), s.getName())).toList();
    }


    public List<SpecialtyIdAndNameDto> allIdAndName() {
        return toIdAndNameDto(findAll());
    }

    @Transactional
    public Specialty createFromDto(SpecialtyCreateDto dto) {
        if(repository.existsByName(dto.getName()))
            throw new SpecialtyCreationException("Назва спеціальності вже зайнята");

        if(repository.existsByNumber(dto.getNumber()))
            throw new SpecialtyCreationException("Номер спеціальності вже зайнятий");

        return save(new Specialty(dto.getName(), dto.getNumber(),
                facultyService.findById(dto.getFacultyId()),
                dto.getBudgetPlaces(), dto.getContractPlaces()));
    }
}
