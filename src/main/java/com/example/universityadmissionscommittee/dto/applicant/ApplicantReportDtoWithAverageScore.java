package com.example.universityadmissionscommittee.dto.applicant;

import java.util.HashMap;

public class ApplicantReportDtoWithAverageScore {

    private ApplicantReportDto base;
    private double averageScore;

    public ApplicantReportDtoWithAverageScore(ApplicantReportDto base,
                                              double averageScore) {
        this.base = base;
        this.averageScore = averageScore;
    }

    public ApplicantReportDto getBase() {
        return base;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public Long getApplicantId() {
        return base.getApplicantId();
    }

    public String getFirstName() {
        return base.getFirstName();
    }

    public String getLastName() {
        return base.getLastName();
    }

    public String getPhoneNumber() {
        return base.getPhoneNumber();
    }

    public String getEmail() {
        return base.getEmail();
    }


    public HashMap<Long, Integer> getSubjectAndScore() {
        return base.getSubjectAndScore();
    }

    public Integer getPriority() {
        return base.getPriority();
    }

    public String getStatus() {
        return base.getStatus();
    }
}
