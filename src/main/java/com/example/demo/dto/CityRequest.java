package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CityRequest {
    @NotNull(message = "Name cannot be null.")
    public String name;

    @Pattern(regexp = "^[A-Z]+$", message = "Code must contain only uppercase letters.")
    @NotNull(message = "Code cannot be null.")
    @Size(min = 1, max = 2, message = "Code must be up to two characters long.")
    public String code;

    @NotNull(message = "Country Id cannot be null.")
    public int countryId;

    // Custom toString method for better debugging
    @Override
    public String toString() {
        return "CityRequest{name='" + name + "', code='" + code + "', countryId=" + countryId + "}";
    }
}
