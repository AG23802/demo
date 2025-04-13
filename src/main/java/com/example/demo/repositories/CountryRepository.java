package com.example.demo.repositories;

import com.example.demo.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
    Country findById(int id);

    Optional<Country> findById(Long id);
}
