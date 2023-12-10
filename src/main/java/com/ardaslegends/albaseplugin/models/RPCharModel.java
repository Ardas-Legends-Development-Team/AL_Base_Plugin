package com.ardaslegends.albaseplugin.models;

import com.ardaslegends.albaseplugin.alapiclients.deseralizers.CustomRPCharDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = CustomRPCharDeserializer.class)
public class RPCharModel {
    private String name;
    private boolean pvp;

    public RPCharModel(String name, boolean pvp) {
        this.name = name;
        this.pvp  = pvp;
    }

    public RPCharModel() {

    }

    public String getName() {
        return name;
    }

    public boolean getPvP() {
        return pvp;
    }
}
