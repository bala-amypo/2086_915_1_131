package com.example.demo.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private final String token;

    public JwtAuthenticationToken(String token) {
        super(null, null);
        this.token = token;
    }

    public JwtAuthenticationToken(
            Object principal,
            Object credentials,
            Collection<? extends GrantedAuthority> authorities,
            String token
    ) {
        super(principal, credentials, authorities);
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
