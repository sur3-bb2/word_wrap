package com.example.football.demo.rest;

import com.example.football.demo.models.Competition;
import com.example.football.demo.models.Country;
import com.example.football.demo.models.Standing;
import com.example.football.demo.models.Team;
import org.springframework.stereotype.Component;

import java.util.List;

public interface FootballApi {
    List<Country> getCountries();

    List<Competition> getCompetitions(String countryId);

    List<Team> getTeams(String competitionId);

    List<Standing> getStandings(String competitionId);
}
