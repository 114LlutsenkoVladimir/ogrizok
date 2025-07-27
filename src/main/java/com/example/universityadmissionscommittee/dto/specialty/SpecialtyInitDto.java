package com.example.universityadmissionscommittee.dto.specialty;

import com.example.universityadmissionscommittee.dto.BenefitIdAndName;
import com.example.universityadmissionscommittee.dto.FacultyIdAndNameDto;
import com.example.universityadmissionscommittee.dto.SubjectIdAndNameDto;

import java.util.List;

public record SpecialtyInitDto (
        List<FacultyIdAndNameDto> allFaculties
) {}

