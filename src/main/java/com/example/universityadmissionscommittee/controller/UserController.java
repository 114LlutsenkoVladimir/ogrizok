package com.example.universityadmissionscommittee.controller;



import com.example.universityadmissionscommittee.service.UserService;
import jakarta.servlet.http.HttpSession;
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
       return (String) session.getAttribute("user");
    }

    @GetMapping("/setUserOnPassword/{password}")
    public String setUserOnPassword(@PathVariable String password,
                                HttpSession session) {
        String user = service.getUser(password);
        session.setAttribute("user", user);
        return user;
    }

}
