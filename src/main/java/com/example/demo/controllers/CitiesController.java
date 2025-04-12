package com.example.demo.controllers;

import com.example.demo.dto.CityRequest;
import com.example.demo.entities.City;
import com.example.demo.dto.CityResponse;
import com.example.demo.exceptions.ErrorResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.demo.services.CityService;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api")
public class CitiesController {
    @Autowired
    CityService cityService;

    @GetMapping("/getCities")
    public List<City> getCities() {
        return cityService.getCities();
    }

    @PostMapping("/saveCity")
    public CityResponse saveCity(@Valid @RequestBody CityRequest cityRequest) {

        System.out.println("After @Valid");
        return cityService.saveCity(cityRequest);
    }

    @RequestMapping("/find")
    public List<City> findCities(@RequestParam int countryId, @RequestParam Long population) {
        return cityService.findCities(countryId, population);
    }

    @RequestMapping("/findCityByCode")
    public List<City> getCityByCode(@RequestParam @Pattern(regexp = "^[A-Z]{1,2}$", message = "Code must be 1 or 2 uppercase letters") String code) {
        return cityService.getCityByCode(code);
    }

    @RequestMapping("/getCity/{cityCode}")
    public ResponseEntity<?> getCity(@PathVariable String cityCode) {
        City city = cityService.getCity(cityCode);

        if (city == null) {
            // Return a 404 status with a structured error response if the city is not found
            ErrorResponse errorResponse = new ErrorResponse("City not found with code " + cityCode, "CITY_NOT_FOUND");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        return ResponseEntity.ok(city);  // Return 200 OK with the city data
    }

    @RequestMapping("/getCount")
    public int getCount() {
        return cityService.getCount();
    }

//    public City getCity(@PathVariable String cityCode) {
//        try {
//            City city = cityService.getCity(cityCode);
//            if (city == null) {
//                throw new RuntimeException("City not found");
//            }
//
//            return city;
//        } catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
//
//    }
}

