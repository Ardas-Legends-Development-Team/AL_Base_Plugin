package com.ardaslegends.albaseplugin.models.BackendModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This is a Model for a Faction.
 * This Model is used for the backend call of getting a Faction from the backend
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BackendFactionModel {
    private String nameOfFaction;
    private String leaderIgn;

    public BackendFactionModel(String nameOfFaction) {
        this.nameOfFaction = nameOfFaction;
    }

    public BackendFactionModel() {

    }

    @Override
    public boolean equals(Object object) {

        if (object != null) {
            if (object.getClass() == this.getClass()) {
                BackendFactionModel otherFactionModel = (BackendFactionModel) object;
                return otherFactionModel.nameOfFaction.equals(this.nameOfFaction);
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return nameOfFaction.hashCode();
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
