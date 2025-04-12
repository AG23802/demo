package com.example.demo.config;

import com.example.demo.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    UserService userService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/home").permitAll() // Allow /home without authentication
                        .requestMatchers(HttpMethod.POST, "/addUser").permitAll()
                        .requestMatchers(HttpMethod.POST, "/addRole").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN") // Allow /home without authentication
                        .anyRequest().authenticated() // All other requests require authentication
                )
                .formLogin(Customizer.withDefaults()); // Enable default login form

        return http.build();
    }

//    @Bean
//    public UserDetailsService users() {
//        return new InMemoryUserDetailsManager(
//                User.withUsername("admin")
//                        .password(passwordEncoder().encode("admin123"))
//                        .roles("ADMIN")
//                        .build(),
//                User.withUsername("user")
//                        .password(passwordEncoder().encode("user123"))
//                        .roles("USER")
//                        .build()
//        );
//    }

    // Add this to your configuration to register it with the AuthenticationManager:
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService); // Custom service to load user details from the database
        authenticationProvider.setPasswordEncoder(passwordEncoder());  // Password encoder (for hashed passwords)
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
