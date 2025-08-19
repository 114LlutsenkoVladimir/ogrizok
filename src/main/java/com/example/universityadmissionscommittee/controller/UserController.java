package com.example.universityadmissionscommittee.controller;



import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/api/me")
public class UserController {
    @GetMapping
    public String getUser(Authentication auth) {

        if (auth == null || !auth.isAuthenticated()
                || auth instanceof org.springframework.security.authentication.AnonymousAuthenticationToken) {
            throw new org.springframework.web.server.ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        return auth.getName();
    }

}
