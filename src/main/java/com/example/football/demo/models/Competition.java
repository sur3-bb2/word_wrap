package com.example.football.demo.models;

import com.fasterxml.jackson.annotation.JsonSetter;

public class Competition {
    private String countryId;
    private String countryName;
    private String leagueId;
    private String leagueName;

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

    public String getLeagueId() {
        return leagueId;
    }

    @JsonSetter("league_id")
    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public String getLeagueName() {
        return leagueName;
    }

    @JsonSetter("league_name")
    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }
}
