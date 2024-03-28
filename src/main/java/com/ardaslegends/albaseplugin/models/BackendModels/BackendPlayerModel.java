package com.ardaslegends.albaseplugin.models.BackendModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This is a Model for a Player.
 * This Model is used for the backend call of getting a Player from the backend
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BackendPlayerModel {
    private String ign;
    private String faction;
    private boolean isStaff;
    private BackendRPCharModel rpChar;

    /*
     * Constructors
     */
    public BackendPlayerModel(String ign, String faction, BackendRPCharModel rpChar, boolean isStaff) {
        this.ign = ign;
        this.faction = faction;
        this.isStaff = isStaff;
        this.rpChar = rpChar;
    }

    public BackendPlayerModel() {

    }

    /*
     * Getter and Setter
     */
    public String getIgn () {
        return ign;
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
