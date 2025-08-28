package com.example.universityadmissionscommittee.exception.exceptionHandler;


import com.example.universityadmissionscommittee.exception.specialty.SpecialtyCreationException;
import com.example.universityadmissionscommittee.exception.specialty.SpecialtyNotFoundException;
import com.example.universityadmissionscommittee.exception.applicant.ApplicantCreationException;
import com.example.universityadmissionscommittee.exception.applicant.ApplicantNotFoundException;
import com.example.universityadmissionscommittee.exception.user.IncorrectPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicantCreationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleApplicantCreationException(ApplicantCreationException ex) {
        return Map.of("message", ex.getMessage());
    }

    @ExceptionHandler(ApplicantNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleApplicantNotFoundException(ApplicantNotFoundException ex) {
        return Map.of("message", ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleGeneric(Exception ex) {
        return Map.of("message", ex.getMessage());
    }

    @ExceptionHandler(SpecialtyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleSpecialtyNotFoundException(SpecialtyNotFoundException ex) {
        return Map.of("message", ex.getMessage());
    }


    @ExceptionHandler(SpecialtyCreationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleSpecialtyCreationException(SpecialtyCreationException ex) {
        return Map.of("message", ex.getMessage());
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleIncorrectPasswordException(IncorrectPasswordException ex) {
        return Map.of("message", ex.getMessage());
    }


}

