package com.example.demo.services;

import com.example.demo.entities.Country;
import com.example.demo.repositories.CountryRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CountryResolver {

    private final CountryRepository countryRepository;

    public CountryResolver(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    // This method will fetch a country for a query like `country(id: ID)`
    public Optional<Country> getCountryById(Long id) {
        return countryRepository.findById(id);
    }
}
