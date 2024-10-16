package com.ardaslegends.albaseplugin.models.BackendModels;

import com.ardaslegends.albaseplugin.alapiclients.deseralizers.CustomClaimbuildDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

/**
 * This is a Model for a Claimbuild.
 * This Model is used for the backend call of getting a Claimbuild from the backend.
 * The Model uses a Custom Deserializer to properly be mapped from a Json String.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = CustomClaimbuildDeserializer.class)
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

    public void setProductionSites (List<BackendProductionSiteModel> prodSites) {
        this.productionSites = prodSites;
    }
}
