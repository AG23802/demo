package com.example.demo.services;

import com.example.demo.entities.City;
import com.example.demo.repositories.CityRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CityResolver {

    private final CityRepository cityRepository;

    public CityResolver(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    // This method will fetch the cities for a query like `cities`
    public List<City> getCities() {
        return cityRepository.findAll();
    }
}