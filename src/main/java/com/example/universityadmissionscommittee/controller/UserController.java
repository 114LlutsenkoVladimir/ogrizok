package com.example.universityadmissionscommittee.controller;



import com.example.universityadmissionscommittee.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/users")
@RestController
public class UserController {
    private UserService service;

    @GetMapping("/getUser")
    public String getUser(HttpSession session) {
       return (String) session.getAttribute("role");
    }

    @GetMapping("/authorization/{password}")
    public String authorization(@PathVariable String password,
                                HttpSession session) {
        String user = service.getUser(password);
        session.setAttribute("role", user);
        return user;
    }

}
