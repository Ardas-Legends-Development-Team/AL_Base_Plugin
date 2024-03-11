package com.ardaslegends.albaseplugin.models.BackendModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This is a Model for a Claimbuild.
 * This Model is used for the backend call of getting a Claimbuild from the backend
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BackendClaimbuildModel {
    String name;
    int regionNr;
    String claimbuildType;

    //ProductionSites

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRegionNr() {
        return regionNr;
    }

    public void setRegionNr (int regionNr) {
        this.regionNr = regionNr;
    }
    public String getClaimbuildType() {
        return claimbuildType;
    }

    public void setClaimbuildType(String claimbuildType) {
        this.claimbuildType = claimbuildType;
    }
}
