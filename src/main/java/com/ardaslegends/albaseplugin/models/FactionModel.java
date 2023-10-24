package com.ardaslegends.albaseplugin.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This is a Model for a Faction.
 * This Model is used for the backend call of getting a Faction from the backend
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FactionModel {
    private String name;

    public FactionModel (String name) {
        this.name = name;
    }

    public FactionModel () {

    }

    public String getName() {
        return name;
    }
}
