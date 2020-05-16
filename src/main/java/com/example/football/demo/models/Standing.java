package com.example.football.demo.models;

import com.fasterxml.jackson.annotation.JsonSetter;

public class Standing {
    private String teamId;
    private String teamName;
    private String position;

    public String getTeamId() {
        return teamId;
    }

    @JsonSetter("team_id")
    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    @JsonSetter("team_name")
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getPosition() {
        return position;
    }

    @JsonSetter("overall_league_position")
    public void setPosition(String position) {
        this.position = position;
    }
}
