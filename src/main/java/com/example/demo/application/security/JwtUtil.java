package com.example.demo.application.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
//Handles everything related to creating, parsing, and validating JWT tokens.
public class JwtUtil {
    private final SecretKey key;

    // Constructor to inject the secret key
    public JwtUtil(@Value("${jwt.secretKey}") String secretKey) {
        this.key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKey)); // Decode Base64 encoded key
    }

    // Debug method to check the injected key
    @PostConstruct
    public void init() {
        System.out.println("Secret Key successfully injected: " + key);
    }

    public String generateToken(String username) {
        // 1 day in ms
        long expiration = 86400000;
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key, Jwts.SIG.HS256) // new style signing
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(key)  // key must be a SecretKey, not a String or byte[]
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}