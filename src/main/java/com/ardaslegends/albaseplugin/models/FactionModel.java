package com.ardaslegends.albaseplugin.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This is a Model for a Faction.
 * This Model is used for the backend call of getting a Faction from the backend
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FactionModel {
    private String nameOfFaction;
    private String leaderIgn;

    public FactionModel (String nameOfFaction) {
        this.nameOfFaction = nameOfFaction;
    }

    public FactionModel () {

    }

    public void setNameOfFaction(String nameOfFaction) {
        this.nameOfFaction = nameOfFaction;
    }

    public void setLeaderIgn(String leaderIgn) {
        this.leaderIgn = leaderIgn;
    }

    public String getName() {
        return nameOfFaction;
    }

    public String getLeader() {
        return leaderIgn;
    }
}
