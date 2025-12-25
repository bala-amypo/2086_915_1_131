package com.example.demo.service;

import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.AppUser;

public interface AppUserService {

    AppUser register(AppUser user);

    AuthResponse login(String email, String password);
}
