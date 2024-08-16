package com.ardaslegends.albaseplugin.models.BackendModels;

/**
 * This is a Model for a ProductionSiteType.
 * This Model is used for the backend call of getting a ProductionSiteType from the backend
 */
public class BackendProductionSiteTypeModel {
    String type;
    String resource;

    /*
     * Constructors
     */
    public BackendProductionSiteTypeModel(String type, String resource) {
        this.type = type;
        this.resource = resource;
    }

    public BackendProductionSiteTypeModel() {
    }

    /*
     * Getter and Setter
     */
    public String getType() {
        return type;
    }

    public String getResource() {
        return resource;
    }
}
