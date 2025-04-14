package com.example.demo.application.services;

import com.example.demo.domain.shared.dto.CityRequest;
import com.example.demo.domain.entities.City;
import com.example.demo.domain.shared.dto.CityResponse;
import com.example.demo.domain.entities.Country;
import com.example.demo.domain.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.demo.domain.repositories.CityRepository;

import java.util.List;

@Service
public class CityService {
    @Autowired
    CityRepository cityRepository;

    @Autowired
    CountryRepository countryRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getCities() {
        return cityRepository.findAll(Sort.by(Sort.Order.asc("id")));
    }

    public List<City> findCities(int countryId, Long population) {
        return cityRepository.findByCountryIdAndPopulationLessThan(countryId, population);
    }

    public City getCity(String cityCode) {
        return cityRepository.findByCode(cityCode);
    }

    public List<City> getCityByCode(String cityCode) {
        return cityRepository.getByCode(cityCode);
    }

    public CityResponse saveCity(CityRequest cityRequest) {
        Country country = countryRepository.findById(cityRequest.countryId);

        City city = new City();
        city.setName(cityRequest.name);
        city.setCode(cityRequest.code);
        city.setCountryId(country.getId());

        City savedCity = cityRepository.save(city);

        CityResponse response = new CityResponse(savedCity);
        response.setCountry(country.getName());
        return response;
    }

    public int getCount() {
        return cityRepository.getCountProcedure();
    }
}
