package com.example.universityadmissionscommittee.exception.specialty;

public class SpecialtyNotFoundException extends RuntimeException{
    public SpecialtyNotFoundException() {
        super("Спеціальність не знайдено");
    }
}
