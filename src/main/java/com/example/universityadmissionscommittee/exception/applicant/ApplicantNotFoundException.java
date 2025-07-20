package com.example.universityadmissionscommittee.exception.applicant;

public class ApplicantNotFoundException extends RuntimeException {
    public ApplicantNotFoundException(String message) {
        super(message);
    }

    public ApplicantNotFoundException() {
        super("Абітурієнт не знайдений");
    }
}
