package com.example.universityadmissionscommittee.controller;



import com.example.universityadmissionscommittee.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/users")
@RestController
public class UserController {
    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/getUser")
    public String getUser(HttpSession session) {
       return (String) session.getAttribute("user");
    }

    @PostMapping("/setUserOnPassword")
    public String setUserOnPassword(@RequestParam String password, HttpSession session) {
        String role = service.getUser(password);
        session.setAttribute("user", role);
        return role;
    }
}
