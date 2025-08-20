package com.example.universityadmissionscommittee.controller;



import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/users")
@RestController
public class UserController {

    @GetMapping("/getUser")
    public String getUser(HttpSession session) {
       return (String) session.getAttribute("role");
    }

    @GetMapping("authorization")
    public String authorization(HttpSession session) {

    }

}
