package com.example.universityadmissionscommittee.data;

import com.example.universityadmissionscommittee.data.enums.ApplicantStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Data
@Entity
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ExamResult> examResults = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "benefit_for_applicant",
            joinColumns = @JoinColumn(name = "applicant_id"),
            inverseJoinColumns = @JoinColumn(name = "benefit_id")
    )
    private Set<Benefit> benefits = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "specialty_for_applicant",
            joinColumns = @JoinColumn(name = "applicant_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id")
    )
    private Set<Specialty> specialties = new HashSet<>();


    @Enumerated(EnumType.STRING)
    private ApplicantStatus statusType = ApplicantStatus.PENDING;


    @Column(name = "status")
    private String status = statusType.toString();

    protected Applicant() {}

    public Applicant(String firstName, String lastName,
                     String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    public void addBenefit(Benefit benefit) {
        benefits.add(benefit);
    }

    public void linkExamResult(ExamResult examResult) {
        examResults.add(examResult);
        examResult.setApplicant(this);
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Set<ExamResult> getExamResults() {
        return examResults;
    }

    public Set<Benefit> getBenefits() {
        return benefits;
    }

    public ApplicantStatus getStatusType() {
        return statusType;
    }

    public String getStatus() {
        return status;
    }


    public void setBenefits(Set<Benefit> benefits) {
        this.benefits = benefits;
    }

    public void addBenefits(Set<Benefit> benefits) {
        for (Benefit benefit : benefits)
            addBenefit(benefit);


    }
    public double calculateAverageScore() {
        double sum = 0;
        for (ExamResult examResult : examResults)
            sum += examResult.getResult();

        sum /= examResults.size();
        for (Benefit benefit : benefits)
            sum += benefit.getAdditionalPoints();
        return sum;
    }

    public int getResultBySubjectId(Long subjectId) {
        return examResults.stream()
                .filter(er -> er.getSubject().getId().equals(subjectId))
                .map(ExamResult::getResult)
                .findFirst().orElseThrow(() -> new NoSuchElementException("exam result not found"));
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setExamResults(Set<ExamResult> examResults) {
        this.examResults = examResults;
    }

    public void addSpecialty(Specialty specialty) {
        this.specialties.add(specialty);
        specialty.getApplicants().add(this);
    }

    public void setStatusType(ApplicantStatus statusType) {
        this.statusType = statusType;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
