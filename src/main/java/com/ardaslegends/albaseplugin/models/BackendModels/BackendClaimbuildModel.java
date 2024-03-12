package com.ardaslegends.albaseplugin.models.BackendModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * This is a Model for a Claimbuild.
 * This Model is used for the backend call of getting a Claimbuild from the backend
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BackendClaimbuildModel {
    String name;
    int region;
    String claimBuildType;
    List<BackendProductionSiteModel> productionSites;

    /*
     * Constructors
     */
    public BackendClaimbuildModel(String name, int region, String claimBuildType) {
        this.name = name;
        this.region = region;
        this.claimBuildType = claimBuildType;
    }

    public BackendClaimbuildModel() {
    }

    /*
     * Getter and Setter
     */
    public String getName() {
        return name;
    }

    public int getRegionNr() {
        return region;
    }

    public String getClaimbuildType() {
        return claimBuildType;
    }

    public List<BackendProductionSiteModel> getProductionSites () {
        return productionSites;
    }
}
