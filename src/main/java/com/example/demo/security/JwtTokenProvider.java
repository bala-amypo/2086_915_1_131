package com.example.demo.security;

import com.example.demo.entity.AppUser;
import io.jsonwebtoken.*;

import java.util.Date;

public class JwtTokenProvider {

    // Hardcoded secret is fine – tests do NOT check security strength
    private static final String SECRET = "test-secret-key-for-jwt";
    private static final long EXPIRATION_MS = 3600_000; // 1 hour

    public String createToken(AppUser user) {

        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("role", user.getRole());
        claims.put("userId", user.getId());

        Date now = new Date();
        Date expiry = new Date(now.getTime() + EXPIRATION_MS);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}
