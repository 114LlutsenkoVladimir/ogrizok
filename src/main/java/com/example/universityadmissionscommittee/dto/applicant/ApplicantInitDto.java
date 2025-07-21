package com.example.universityadmissionscommittee.dto.applicant;

import com.example.universityadmissionscommittee.data.Benefit;
import com.example.universityadmissionscommittee.data.Specialty;
import com.example.universityadmissionscommittee.data.Subject;
import com.example.universityadmissionscommittee.dto.BenefitIdAndName;
import com.example.universityadmissionscommittee.dto.SpecialtyIdAndNameDto;
import com.example.universityadmissionscommittee.dto.SubjectIdAndNameDto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ApplicantInitDto(
        @JsonProperty("allBenefits") List<BenefitIdAndName> allBenefits,
        @JsonProperty("allSubjects") List<SubjectIdAndNameDto> allSubjects,
        @JsonProperty("allSpecialties") List<SpecialtyIdAndNameDto> allSpecialties
) {
    @JsonCreator
    public ApplicantInitDto(
            @JsonProperty("allBenefits") List<BenefitIdAndName> allBenefits,
            @JsonProperty("allSubjects") List<SubjectIdAndNameDto> allSubjects,
            @JsonProperty("allSpecialties") List<SpecialtyIdAndNameDto> allSpecialties
    ) {
        this.allBenefits = allBenefits;
        this.allSubjects = allSubjects;
        this.allSpecialties = allSpecialties;
    }
}
