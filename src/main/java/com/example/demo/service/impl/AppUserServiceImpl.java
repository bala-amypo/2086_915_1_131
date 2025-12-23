package com.example.demo.service.impl;

import com.example.demo.entity.AppUser;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.service.AppUserService;

public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository userRepository;

    public AppUserServiceImpl(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AppUser createUser(AppUser user) {
        return userRepository.save(user);
    }

    @Override
    public AppUser getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}
