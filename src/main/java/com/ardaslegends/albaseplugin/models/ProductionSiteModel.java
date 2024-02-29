package com.ardaslegends.albaseplugin.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductionSiteModel {
    private String type;
    private String resource;

    public ProductionSiteModel () {

    }

    public ProductionSiteModel (String type, String resource) {
        this.type = type;
        this.resource = resource;
    }

    public String getType() {
        return type;
    }

    public String getResource() {
        return resource;
    }

    public String getResourceMCID() {
        //TODO: get the MC-ID from the resource String and return it
        return null;
    }
}
