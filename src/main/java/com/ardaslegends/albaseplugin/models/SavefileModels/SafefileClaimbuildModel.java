package com.ardaslegends.albaseplugin.models.SavefileModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * This is a Model for a Claimbuild.
 * This Model is used to load a Claimbuild from the Safefiles
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SafefileClaimbuildModel {
    String name;
    String claimbuildType;
    List<SafefileResourceModel> resources;

    public SafefileClaimbuildModel(String name) {
        this.name = name;
    }

    public SafefileClaimbuildModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClaimbuildType() {
        return claimbuildType;
    }

    public void setClaimbuildType (String claimbuildType) {
        this.claimbuildType = claimbuildType;
    }

    public List<SafefileResourceModel> getResources() {
        return resources;
    }

    public void setResources(List<SafefileResourceModel> resources) {
        this.resources = resources;
    }
}
