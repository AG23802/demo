package com.example.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/protected")
public class ProtectedController {

    @GetMapping("/data")
    public ResponseEntity<String> getData() {
        // This data is protected and can only be accessed with a valid JWT token
        return ResponseEntity.ok("This is protected data!");
    }
}