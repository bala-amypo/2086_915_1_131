package com.example.demo.controller;

import com.example.demo.entity.AppUser;
import com.example.demo.service.AuthService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Register a new user
     */
    @PostMapping("/register")
    public AppUser register(@RequestBody AppUser user) {
        return authService.register(user);
    }

    /**
     * Login and receive JWT token
     */
    @PostMapping("/login")
    public String login(@RequestBody AppUser user) {
        return authService.login(user.getEmail(), user.getPassword());
    }
}
