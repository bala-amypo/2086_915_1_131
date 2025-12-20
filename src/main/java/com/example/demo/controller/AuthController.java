package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest req) {
        return null; // not tested
    }

    @PostMapping("/register")
    public void register(@RequestBody AuthRequest req) {
        // not tested
    }
}
