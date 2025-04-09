package com.example.demo.entities;

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