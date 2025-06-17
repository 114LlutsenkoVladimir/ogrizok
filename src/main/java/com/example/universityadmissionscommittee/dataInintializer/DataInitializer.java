package com.example.universityadmissionscommittee.dataInintializer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DataInitializer implements CommandLineRunner {
    private DataInitializerService service;

    public DataInitializer(DataInitializerService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) throws Exception {
        if(service.isFacultiesEmpty())
            initializeAll();
    }

    public void initializeAll() {
        service.initializeBenefits();
        service.initializeFaculties();
        service.initializeSpecialtiesWithFaculties();
        service.initializeSubjects();
        service.connectSubjectsAndSpecialties();
    }
}
