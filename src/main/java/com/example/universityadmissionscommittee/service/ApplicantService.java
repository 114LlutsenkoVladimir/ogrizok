package com.example.universityadmissionscommittee.service;

import com.example.universityadmissionscommittee.data.Applicant;
import com.example.universityadmissionscommittee.data.Benefit;
import com.example.universityadmissionscommittee.data.Specialty;
import com.example.universityadmissionscommittee.repository.ApplicantRepository;
import com.example.universityadmissionscommittee.repository.ExamResultRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ApplicantService extends AbstractCrudService<Applicant, Long, ApplicantRepository> {

    private final ExamResultRepository examResultRepository;

    protected ApplicantService(ApplicantRepository repository,
                               ExamResultRepository examResultRepository) {
        super(repository);
        this.examResultRepository = examResultRepository;
    }

    @Override
    public void deleteById(Long id) {
        Applicant applicant = findById(id);
        examResultRepository.deleteAll(applicant.getExamResults());
        applicant.getBenefits().clear();
        repository.deleteById(id);
    }


    public List<Applicant> getSortedApplicantListWithSpecialty(Specialty specialty) {
        return findAll().stream().filter(
                i -> i.getSpecialty().equals(specialty)
        ).sorted(Comparator.comparingDouble(Applicant::calculateAverageScore).reversed()).toList();
    }

    public Applicant findByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(
                NoSuchElementException::new
        );
    }

    public Applicant findByPhoneNumber(String phoneNumber) {
        return  repository.findByPhoneNumber(phoneNumber).orElseThrow(
                NoSuchElementException::new
        );
    }


}
