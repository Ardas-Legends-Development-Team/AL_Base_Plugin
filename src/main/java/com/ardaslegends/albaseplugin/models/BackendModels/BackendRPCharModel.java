package com.ardaslegends.albaseplugin.models.BackendModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BackendRPCharModel {
    private String name;
    private boolean pvp;

    /*
     * Constructors
     */
    public BackendRPCharModel(String name, boolean pvp) {
        this.name = name;
        this.pvp  = pvp;
    }

    public BackendRPCharModel() {

    }

    /*
     * Getter and Setter
     */
    public String getName() {
        return name;
    }

    public boolean getPvP() {
        return pvp;
    }
}
