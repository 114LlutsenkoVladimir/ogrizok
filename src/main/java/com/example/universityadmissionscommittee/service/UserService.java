package com.example.universityadmissionscommittee.service;

import com.example.universityadmissionscommittee.exception.user.IncorrectPasswordException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String getUser(String password) {
        switch (password) {
            case "admin" -> {
                return "admin";
            }
            case "committee" -> {
                return "committee";
            }
            case "" -> {
                return "user";
            }
            default -> throw new IncorrectPasswordException();
        }
    }
}
