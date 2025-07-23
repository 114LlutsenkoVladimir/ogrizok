package com.example.universityadmissionscommittee.dto.applicant;

import com.example.universityadmissionscommittee.dto.BenefitIdAndName;
import com.example.universityadmissionscommittee.dto.specialty.SpecialtyIdAndNameDto;
import com.example.universityadmissionscommittee.dto.SubjectIdAndNameDto;

import java.util.List;

public record ApplicantInitDto(
        List<BenefitIdAndName> allBenefits,
        List<SubjectIdAndNameDto> allSubjects,
        List<SpecialtyIdAndNameDto> allSpecialties
) {}

