package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.AppUser;
import com.example.demo.service.AppUserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AppUserService appUserService;

    public AuthController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    // Register a new user
    @PostMapping("/register")
    public AppUser register(@RequestBody AuthRequest request) {
        return appUserService.register(
                request.getEmail(),
                request.getPassword(),
                "ROLE_USER"
        );
    }

    // Login and get JWT token
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return appUserService.login(
                request.getEmail(),
                request.getPassword()
        );
    }
}
