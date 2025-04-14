package com.example.demo.api.rest.controllers;

import com.example.demo.domain.entities.User;
import com.example.demo.application.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @Autowired
    UserService userService;

    @RequestMapping("/home")
    public String home() {
        return "Hello World!";
    }

    @RequestMapping("/admin")
    public String adminHome() {
        return "Hello Admin!";
    }

    @PostMapping( "/addUser")
    public User addUser(@RequestBody User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userService.addUser(user);
    }

}
