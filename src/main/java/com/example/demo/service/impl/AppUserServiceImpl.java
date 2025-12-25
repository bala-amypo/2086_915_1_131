package com.example.demo.service.impl;

import com.example.demo.entity.AppUser;
import com.example.demo.exception.ResourceNotFoundException;
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
    public AppUser getUserByEmail(String email) {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with email: " + email));
    }
}
