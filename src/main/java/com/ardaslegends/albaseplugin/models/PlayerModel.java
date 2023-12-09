package com.ardaslegends.albaseplugin.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRawValue;

/**
 * This is a Model for a Player.
 * This Model is used for the backend call of getting a Player from the backend
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerModel {
    private String ign;
    private String faction;
    private boolean isStaff;
    private RPCharModel rpChar;

    public PlayerModel(String ign, String faction, RPCharModel rpChar, boolean isStaff) {
        this.ign = ign;
        this.faction = faction;
    }

    public PlayerModel() {

    }

    public String getFaction() {
        return faction;
    }

    public boolean getIsStaff() {
        return isStaff;
    }

    public RPCharModel getRpChar() {
        return rpChar;
    }
}
