package com.ardaslegends.albaseplugin.models.BackendModels;

import com.ardaslegends.albaseplugin.alapiclients.deseralizers.CustomPlayerDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * This is a Model for a Player.
 * This Model is used for the backend call of getting a Player from the backend
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = CustomPlayerDeserializer.class)
public class BackendPlayerModel {
    private String ign;
    private String faction;
    private boolean isStaff;
    private BackendRPCharModel rpChar;

    public BackendPlayerModel(String ign, String faction, BackendRPCharModel rpChar, boolean isStaff) {
        this.ign = ign;
        this.faction = faction;
        this.isStaff = isStaff;
        this.rpChar = rpChar;
    }

    public BackendPlayerModel() {

    }

    public String getFaction() {
        return faction;
    }

    public boolean getIsStaff() {
        return isStaff;
    }

    public BackendRPCharModel getRpChar() {
        return rpChar;
    }
}
