package com.example.football.demo.rest;

import com.example.football.demo.models.Competition;
import com.example.football.demo.models.Country;
import com.example.football.demo.models.Standing;
import com.example.football.demo.models.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Client implements FootballApi {
    RestTemplate restTemplate = new RestTemplate();

    private static final String URL = "https://apiv2.apifootball.com/?APIkey=9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978";
    private static final String COUNTRY_GET  = URL + "&action=get_countries";
    private static final String COMPETITION_GET  = URL + "&action=get_leagues&country_id=";
    private static final String TEAM_GET  = URL + "&action=get_teams&league_id=";
    private static final String STANDING_GET  = URL + "&action=get_standings&league_id=";

    @Override
    public List<Country> getCountries() {
        return restTemplate.exchange(COUNTRY_GET, HttpMethod.GET, null, new ParameterizedTypeReference<List<Country>>() {
        }).getBody();
    }

    @Override
    public List<Competition> getCompetitions(String countryId) {
        return restTemplate.exchange(COMPETITION_GET + countryId,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Competition>>() {
        }).getBody();
    }

    @Override
    public List<Team> getTeams(String competitionId) {
        return restTemplate.exchange(TEAM_GET + competitionId,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Team>>() {
                }).getBody();
    }

    @Override
    public List<Standing> getStandings(String competitionId) {
        return restTemplate.exchange(STANDING_GET + competitionId,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Standing>>() {
                }).getBody();
    }
}
