package com.ardaslegends.albaseplugin.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
