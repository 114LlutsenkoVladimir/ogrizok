package com.example.universityadmissionscommittee.exception;

public class SpecialtyNotFoundException extends RuntimeException{
    public SpecialtyNotFoundException() {
        super("Спеціальність не знайдено");
    }
}
