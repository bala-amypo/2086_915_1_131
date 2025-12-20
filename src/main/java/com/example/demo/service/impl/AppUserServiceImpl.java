package com.example.demo.service.impl;

import com.example.demo.entity.AppUser;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.service.AppUserService;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;

    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public AppUser register(String email, String password, String role) {

        appUserRepository.findByEmail(email)
                .ifPresent(u -> { throw new RuntimeException("unique"); });

        AppUser user = AppUser.builder()
                .email(email)
                .password(password)
                .role(role)
                .active(true)
                .build();

        return appUserRepository.save(user);
    }
}
