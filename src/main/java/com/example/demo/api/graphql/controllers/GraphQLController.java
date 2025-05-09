package com.example.demo.api.graphql.controllers;

import com.example.demo.api.graphql.dto.TokenResponse;
import com.example.demo.domain.shared.dto.CityRequest;
import com.example.demo.domain.shared.dto.CityResponse;
import com.example.demo.domain.entities.City;
import com.example.demo.application.security.JwtUtil;
import com.example.demo.application.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GraphQLController {

    @Autowired
    private CityService cityService;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    JwtUtil jwtUtil;

    @QueryMapping
    public List<City> cities() {
        return cityService.getCities();
    }

    // Query to get a city by ID
    @QueryMapping(name = "cityByCode")
    // ony needed if method name doesnt match to schema
    public City getCity(@Argument("code") String code) {
        return cityService.getCity(code);
    }

    @MutationMapping
    public CityResponse createCity(@Argument CityRequest cityRequest) {
        return cityService.saveCity(cityRequest);
    }

    // Query to get a city by ID
    @MutationMapping
    // ony needed if method name doesnt match to schema
    public TokenResponse login(@Argument("username") String username, @Argument("password") String password) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        String token = jwtUtil.generateToken(username);
        return new TokenResponse(token);
    }
}