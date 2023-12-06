package com.ardaslegends.albaseplugin.models;

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
