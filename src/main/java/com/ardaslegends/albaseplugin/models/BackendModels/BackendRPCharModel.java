package com.ardaslegends.albaseplugin.models.BackendModels;

import com.ardaslegends.albaseplugin.alapiclients.deseralizers.CustomRPCharDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = CustomRPCharDeserializer.class)
public class BackendRPCharModel {
    private String name;
    private boolean pvp;

    public BackendRPCharModel(String name, boolean pvp) {
        this.name = name;
        this.pvp  = pvp;
    }

    public BackendRPCharModel() {

    }

    public String getName() {
        return name;
    }

    public boolean getPvP() {
        return pvp;
    }
}
