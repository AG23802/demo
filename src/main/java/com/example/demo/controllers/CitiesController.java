package com.example.demo.controllers;

import com.example.demo.Pojos.CityRequest;
import com.example.demo.entities.City;
import com.example.demo.entities.CityResponse;
import com.example.demo.exceptions.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.services.CityService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CitiesController {
    @Autowired
    CityService cityService;

    @GetMapping("/getCities")
    public List<City> getCities() {
        return cityService.getCities();
    }

    @PostMapping("/saveCity")
    public City saveCity(@RequestBody City city) {
        return cityService.saveCity(city);
    }

    @PostMapping("/addCity")
    public CityResponse addCity(@RequestBody CityRequest cityRequest) {
        return cityService.addCity(cityRequest);
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
