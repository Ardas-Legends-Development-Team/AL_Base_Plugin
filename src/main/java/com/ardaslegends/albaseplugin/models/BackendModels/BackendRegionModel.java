package com.ardaslegends.albaseplugin.models.BackendModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This is a Model for a Region.
 * This Model is used for the backend call of getting a Region from the backend
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BackendRegionModel {
    int id;
    String name;
    String region_type;

    public BackendRegionModel(int id, String name, String region_type) {
        this.id = id;
        this.name = name;
        this.region_type = region_type;
    }

    public BackendRegionModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion_type() {
        return region_type;
    }

    public void setRegion_type(String region_type) {
        this.region_type = region_type;
    }
}
