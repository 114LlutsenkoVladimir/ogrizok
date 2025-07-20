package com.example.universityadmissionscommittee.dto.applicant;

import com.example.universityadmissionscommittee.data.Benefit;
import com.example.universityadmissionscommittee.data.Specialty;
import com.example.universityadmissionscommittee.data.Subject;

import java.util.ArrayList;
import java.util.List;

public class ApplicantInitDto {

    List<Benefit> allBenefits = new ArrayList<>();
    List<Subject> allSubjects = new ArrayList<>();
    List<Specialty> allSpecialties = new ArrayList<>();

    public ApplicantInitDto(List<Benefit> allBenefits,
                            List<Subject> allSubjects,
                            List<Specialty> allSpecialties) {
        this.allBenefits = allBenefits;
        this.allSubjects = allSubjects;
        this.allSpecialties = allSpecialties;
    }

    public List<Benefit> getAllBenefits() {
        return allBenefits;
    }

    public List<Subject> getAllSubjects() {
        return allSubjects;
    }

    public List<Specialty> getAllSpecialties() {
        return allSpecialties;
    }
}
