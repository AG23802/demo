package com.example.demo.dto;

public class TokenResponse {
    public void setToken(String token) {
        this.token = token;
    }

    private String token;
    public TokenResponse(String token) { this.token = token; }
    public String getToken() { return token; }
}
