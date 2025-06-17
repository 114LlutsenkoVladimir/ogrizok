package com.example.universityadmissionscommittee;

import com.example.universityadmissionscommittee.service.BenefitService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class UniversityAdmissionsCommitteeApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniversityAdmissionsCommitteeApplication.class, args);


    }

}
