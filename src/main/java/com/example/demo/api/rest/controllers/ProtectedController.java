package com.example.demo.api.rest.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/protected")
public class ProtectedController {

    @GetMapping("/data")
    public ResponseEntity<Map<String, String>> getData() {
        // This data is protected and can only be accessed with a valid JWT token
        return ResponseEntity.ok(Map.of("message", "This is protected data!"));
    }
}