package com.example.football.demo;

import com.example.football.demo.models.Competition;
import com.example.football.demo.models.Country;
import com.example.football.demo.models.Standing;
import com.example.football.demo.models.Team;
import com.example.football.demo.rest.Client;
import com.example.football.demo.rest.FootballApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Validated
public class FootballController {
    @Autowired
    FootballApi footballApi;

    @GetMapping("/countries")
    @CachePut
    public List<Country> getCountries() {
        return footballApi.getCountries();
    }

    @GetMapping("/competitions")
    @CachePut
    public List<Competition> getCompetitions(@RequestParam(value = "countryId") @NotNull @NotBlank String countryId) {
        return footballApi.getCompetitions(countryId);
    }

    @GetMapping("/teams")
    public List<Team> getTeams(@RequestParam(value = "competitionId") @NotNull @NotBlank String competitionId) {
        return footballApi.getTeams(competitionId);
    }

    @GetMapping("/standings")
    public List<Standing> getStandings(@RequestParam(value = "competitionId") @NotNull @NotBlank String competitionId) {
        return footballApi.getStandings(competitionId);
    }
}
