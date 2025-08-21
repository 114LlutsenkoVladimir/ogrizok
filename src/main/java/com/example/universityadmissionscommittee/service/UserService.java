package com.example.universityadmissionscommittee.service;

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
            default -> throw new RuntimeException("Невірний пароль");
        }
    }
}
