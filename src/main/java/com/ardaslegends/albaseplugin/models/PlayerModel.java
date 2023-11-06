package com.ardaslegends.albaseplugin.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This is a Model for a Player.
 * This Model is used for the backend call of getting a Player from the backend
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerModel {
    private String ign;
    private String faction;

    public PlayerModel(String ign, String faction) {
        this.ign = ign;
        this.faction = faction;
    }

    public PlayerModel() {

    }

    public String getFaction() {
        return faction;
    }
}
