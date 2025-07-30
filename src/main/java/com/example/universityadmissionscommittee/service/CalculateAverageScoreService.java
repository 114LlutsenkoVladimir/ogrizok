package com.example.universityadmissionscommittee.service;

import com.example.universityadmissionscommittee.dto.BenefitIdNamePoints;
import com.example.universityadmissionscommittee.dto.applicant.ApplicantReportDto;

public class CalculateAverageScoreService {
    public double calculate(ApplicantReportDto applicant) {
        double res = 0;
        res += applicant.getSubjectAndScore().values().stream().mapToInt(Integer::intValue).sum();
        res /= applicant.getSubjectAndScore().size();
        res /= applicant.getPriority();
        res += applicant.getBenefits().stream().mapToInt(BenefitIdNamePoints::points).sum();
        return res;
    }
}
