package com.example.football.demo;

import com.example.football.demo.models.Competition;
import com.example.football.demo.models.Country;
import com.example.football.demo.models.Standing;
import com.example.football.demo.models.Team;
import com.example.football.demo.rest.FootballApi;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.internal.verification.AtMost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FootballController.class)
class FootballControllerTest {
    @Autowired
    public MockMvc mockMvc;

    @MockBean
    public FootballApi footballApi;

    @Test
    void getCountries() throws Exception {
        ArrayList<Country> expected = new ArrayList<>();
        Country india = new Country();
        india.setCountryId("1"); india.setCountryName("India");
        expected.add(india);
        BDDMockito.given(footballApi.getCountries()).willReturn(expected);

        this.mockMvc.perform(
                get("/countries"))
                .andDo(print())
                .andExpect(status().isOk())
                    .andExpect(content().string(org.hamcrest.CoreMatchers.containsStringIgnoringCase("India")));

        BDDMockito.verify(footballApi, Mockito.atMostOnce()).getCountries();
    }

    @Test
    void getCompetitions400() throws Exception {
        this.mockMvc.perform(
                get("/competitions"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void getCompetitions() throws Exception {
        ArrayList<Competition> expected = new ArrayList<>();
        Competition isl = new Competition();
        isl.setCountryId("1"); isl.setCountryName("India"); isl.setLeagueId("122"); isl.setLeagueName("India Super League");
        expected.add(isl);

        BDDMockito.given(footballApi.getCompetitions("1")).willReturn(expected);

        this.mockMvc.perform(
                get("/competitions?countryId=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.CoreMatchers.containsStringIgnoringCase("India Super League")));

        BDDMockito.verify(footballApi, Mockito.atMostOnce()).getCompetitions("1");
    }

    @Test
    void getTeams() throws Exception {
        ArrayList<Team> expected = new ArrayList<>();
        Team team = new Team();
        team.setTeamKey("CSK"); team.setTeamName("Chennai Super Kings");
        expected.add(team);

        BDDMockito.given(footballApi.getTeams("1")).willReturn(expected);

        this.mockMvc.perform(
                get("/teams?competitionId=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.CoreMatchers.containsStringIgnoringCase("Chennai Super Kings")));

        BDDMockito.verify(footballApi, Mockito.atMostOnce()).getTeams("1");
    }

    @Test
    void getStandings() throws Exception {
        ArrayList<Standing> expected = new ArrayList<>();
        Standing standing = new Standing();
        standing.setTeamId("CHE"); standing.setTeamName("Chelsea");standing.setPosition("1");
        expected.add(standing);

        BDDMockito.given(footballApi.getStandings("1")).willReturn(expected);

        this.mockMvc.perform(
                get("/standings?competitionId=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.CoreMatchers.containsStringIgnoringCase("Chelsea")));

        BDDMockito.verify(footballApi, Mockito.atMostOnce()).getStandings("1");
    }
}