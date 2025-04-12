package com.example.demo.dto;

import com.example.demo.entities.City;

public class CityResponse {
    private String name;
    private String code;
    private String country;

    public CityResponse(City city) {
        this.name = city.getName();
        this.code = city.getCode();
    }

    public void setCountry(String country) {
        this.country = country;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getCountry() {
        return country;
    }
}