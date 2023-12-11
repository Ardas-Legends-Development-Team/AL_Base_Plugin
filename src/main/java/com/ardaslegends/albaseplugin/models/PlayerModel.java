package com.ardaslegends.albaseplugin.models;

import com.ardaslegends.albaseplugin.alapiclients.deseralizers.CustomPlayerDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * This is a Model for a Player.
 * This Model is used for the backend call of getting a Player from the backend
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = CustomPlayerDeserializer.class)
public class PlayerModel {
    private String ign;
    private String faction;
    private boolean isStaff;
    private RPCharModel rpChar;

    public PlayerModel(String ign, String faction, RPCharModel rpChar, boolean isStaff) {
        this.ign = ign;
        this.faction = faction;
        this.isStaff = isStaff;
        this.rpChar = rpChar;
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
