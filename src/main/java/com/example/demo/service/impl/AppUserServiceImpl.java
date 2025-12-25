package com.example.demo.service.impl;

import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.AppUser;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.AppUserService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AppUserServiceImpl(
            AppUserRepository appUserRepository,
            PasswordEncoder passwordEncoder,
            JwtTokenProvider jwtTokenProvider
    ) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public AppUser register(AppUser user) {
        if (appUserRepository.existsByEmail(user.getEmail())) {
            throw new BadRequestException("Email already registered");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);

        return appUserRepository.save(user);
    }

    @Override
    public AuthResponse login(String email, String password) {
        AppUser user = appUserRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadRequestException("Invalid credentials");
        }

        String token = jwtTokenProvider.createToken(user);

        return new AuthResponse(token);
    }
}
