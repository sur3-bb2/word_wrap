package com.example.football.demo.models;

import com.fasterxml.jackson.annotation.JsonSetter;

public class Country {
    private String countryId;
    private String countryName;

    public String getCountryId() {
        return countryId;
    }

    @JsonSetter("country_id")
    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    @JsonSetter("country_name")
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
