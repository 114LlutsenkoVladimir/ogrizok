package com.example.universityadmissionscommittee.dataInintializer;

import com.example.universityadmissionscommittee.data.Benefit;
import com.example.universityadmissionscommittee.data.Faculty;
import com.example.universityadmissionscommittee.data.Specialty;
import com.example.universityadmissionscommittee.data.Subject;
import com.example.universityadmissionscommittee.data.enums.BenefitType;
import com.example.universityadmissionscommittee.data.enums.FacultyType;
import com.example.universityadmissionscommittee.data.enums.SpecialtyType;
import com.example.universityadmissionscommittee.data.enums.SubjectType;
import com.example.universityadmissionscommittee.service.BenefitService;
import com.example.universityadmissionscommittee.service.FacultyService;
import com.example.universityadmissionscommittee.service.SpecialtyService;
import com.example.universityadmissionscommittee.service.SubjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class DataInitializerService {
    private BenefitService benefitService;
    private FacultyService facultyService;
    private SpecialtyService specialtyService;
    private SubjectService subjectService;

    public DataInitializerService (BenefitService benefitService,
                           FacultyService facultyService,
                           SpecialtyService specialtyService,
                           SubjectService subjectService) {
        this.benefitService = benefitService;
        this.facultyService = facultyService;
        this.specialtyService = specialtyService;
        this.subjectService = subjectService;
    }

    @Transactional
    public void connectSubjectsAndSpecialties() {
        Subject biology = subjectService.findBySubjectType(SubjectType.BIOLOGY);
        Subject chemistry = subjectService.findBySubjectType(SubjectType.CHEMISTRY);
        Subject historyOfUkraine = subjectService.findBySubjectType(SubjectType.HISTORY_OF_UKRAINE);
        Subject english = subjectService.findBySubjectType(SubjectType.ENGLISH);
        Subject mathematics = subjectService.findBySubjectType(SubjectType.MATHEMATICS);
        Subject ukrainianLanguage = subjectService.findBySubjectType(SubjectType.UKRAINIAN_LANGUAGE);

        Specialty medicine = specialtyService.findBySpecialtyType(SpecialtyType.MEDICINE);
        Specialty pharmacy = specialtyService.findBySpecialtyType(SpecialtyType.PHARMACY);
        Specialty law = specialtyService.findBySpecialtyType(SpecialtyType.LAW);
        Specialty internationalLaw = specialtyService.findBySpecialtyType(SpecialtyType.INTERNATIONAL_LAW);
        Specialty computerScience = specialtyService.findBySpecialtyType(SpecialtyType.COMPUTER_SCIENCE);
        Specialty cyberSecurity = specialtyService.findBySpecialtyType(SpecialtyType.CYBERSECURITY);

        medicine.linkSubject(biology);
        medicine.linkSubject(chemistry);
        pharmacy.linkSubject(biology);
        pharmacy.linkSubject(chemistry);
        law.linkSubject(historyOfUkraine);
        law.linkSubject(ukrainianLanguage);
        law.linkSubject(mathematics);
        internationalLaw.linkSubject(historyOfUkraine);
        internationalLaw.linkSubject(ukrainianLanguage);
        internationalLaw.linkSubject(english);
        computerScience.linkSubject(mathematics);
        computerScience.linkSubject(english);
        computerScience.linkSubject(ukrainianLanguage);
        cyberSecurity.linkSubject(mathematics);
        cyberSecurity.linkSubject(english);
        cyberSecurity.linkSubject(ukrainianLanguage);
    }
    public void initializeBenefits() {
        for (BenefitType bt : BenefitType.values()) {
            Benefit benefit = new Benefit(bt);
            benefitService.save(benefit);
        }
    }
    public void initializeSubjects() {
        for (SubjectType st : SubjectType.values()) {
            subjectService.save(new Subject(st));
        }
    }

    public void initializeFaculties() {
        for (FacultyType ft : FacultyType.values())
            facultyService.save(new Faculty(ft));
    }
    public void initializeSpecialtiesWithFaculties() {

        List<Specialty> specialties = new ArrayList<>(List.of(
                new Specialty(121,
                        facultyService.findByFacultyType(FacultyType.COMPUTER_SCIENCE_FACULTY),
                10, 20, SpecialtyType.COMPUTER_SCIENCE),

                new Specialty(122,
                        facultyService.findByFacultyType(FacultyType.COMPUTER_SCIENCE_FACULTY),
                20, 20, SpecialtyType.CYBERSECURITY),

                new Specialty(221,
                        facultyService.findByFacultyType(FacultyType.LAW_FACULTY),
                10, 15, SpecialtyType.LAW),

                new Specialty(222,
                        facultyService.findByFacultyType(FacultyType.LAW_FACULTY),
                        10, 20, SpecialtyType.INTERNATIONAL_LAW),

                new Specialty(321,
                        facultyService.findByFacultyType(FacultyType.MEDICAL_FACULTY),
                        20, 20, SpecialtyType.MEDICINE),

                new Specialty(322,
                        facultyService.findByFacultyType(FacultyType.MEDICAL_FACULTY),
                        14, 14, SpecialtyType.PHARMACY)
        ));

        for (Specialty sp : specialties) {
            specialtyService.save(sp);
        }

    }

    public boolean isFacultiesEmpty() {
        return facultyService.findAll().isEmpty();
    }
}
