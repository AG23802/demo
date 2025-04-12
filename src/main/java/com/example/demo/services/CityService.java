package com.example.demo.services;

import com.example.demo.Pojos.CityRequest;
import com.example.demo.entities.City;
import com.example.demo.entities.CityResponse;
import com.example.demo.entities.Country;
import com.example.demo.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.demo.repositories.CityRepository;

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

        System.out.println("Before @Valid");
        City savedCity = cityRepository.save(city);

        CityResponse response = new CityResponse(savedCity);
        response.setCountry(country.getName());
        return response;
    }

    public int getCount() {
        return cityRepository.getCountProcedure();
    }
}
