package com.example.demo.service.impl;

import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.AppUser;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.UnauthorizedException;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.AppUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtTokenProvider jwtProvider;

    public AppUserServiceImpl(
            AppUserRepository repo,
            PasswordEncoder encoder,
            JwtTokenProvider jwtProvider
    ) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public void register(String email, String password, String role) {

        if (repo.findByEmail(email).isPresent()) {
            throw new BadRequestException("User already exists");
        }

        AppUser user = AppUser.builder()
                .email(email)
                .password(encoder.encode(password))
                .role(role)
                .build();

        repo.save(user);
    }

    @Override
    public AuthResponse login(String email, String password) {

        AppUser user = repo.findByEmail(email)
                .orElseThrow(() -> new UnauthorizedException("Invalid credentials"));

        if (!encoder.matches(password, user.getPassword())) {
            throw new UnauthorizedException("Invalid credentials");
        }

        String token = jwtProvider.generateToken(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );

        return AuthResponse.builder()
                .token(token)
                .userId(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
