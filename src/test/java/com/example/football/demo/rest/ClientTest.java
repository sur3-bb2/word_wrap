package com.example.football.demo.rest;

import com.example.football.demo.models.Competition;
import com.example.football.demo.models.Country;
import com.example.football.demo.models.Standing;
import com.example.football.demo.models.Team;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void getCountries() {
        FootballApi classUnderTest = new Client();

        List<Country> countries = classUnderTest.getCountries();

        assertNotNull(countries);
        assertTrue(countries.size() > 0);
    }

    @Test
    void getCompetitions() {
        FootballApi classUnderTest = new Client();

        List<Competition> competitions = classUnderTest.getCompetitions("41");

        assertNotNull(competitions);
        assertTrue(competitions.size() > 0);
    }

    @Test
    void getTeams() {
        FootballApi classUnderTest = new Client();

        List<Team> teams = classUnderTest.getTeams("148");

        assertNotNull(teams);
        assertTrue(teams.size() > 0);
    }

    @Test
    void getStandings() {
        FootballApi classUnderTest = new Client();

        List<Standing> standings = classUnderTest.getStandings("148");

        assertNotNull(standings);
        assertTrue(standings.size() > 0);
    }
}