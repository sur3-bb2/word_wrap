package com.example.football.demo.models;

import com.fasterxml.jackson.annotation.JsonSetter;

public class Team {
    private String teamKey;
    private String teamName;

    public String getTeamKey() {
        return teamKey;
    }

    @JsonSetter("team_key")
    public void setTeamKey(String teamKey) {
        this.teamKey = teamKey;
    }

    public String getTeamName() {
        return teamName;
    }

    @JsonSetter("team_name")
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
