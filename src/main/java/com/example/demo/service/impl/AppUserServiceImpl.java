package com.example.demo.service.impl;

import com.example.demo.entity.AppUser;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.service.AppUserService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository repo;
    private final PasswordEncoder passwordEncoder;

    public AppUserServiceImpl(AppUserRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUser register(AppUser user) {

        repo.findByEmail(user.getEmail()).ifPresent(u -> {
            throw new BadRequestException("unique");
        });

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getActive() == null)
            user.setActive(true);

        return repo.save(user);
    }

    @Override
    public AppUser getByEmail(String email) {
        return repo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}