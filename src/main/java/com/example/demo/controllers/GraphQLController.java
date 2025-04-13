package com.example.demo.controllers;

import com.example.demo.dto.CityRequest;
import com.example.demo.dto.CityResponse;
import com.example.demo.entities.City;
import com.example.demo.entities.Country;
import com.example.demo.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class GraphQLController {

    @Autowired
    private CityService cityService;

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
}