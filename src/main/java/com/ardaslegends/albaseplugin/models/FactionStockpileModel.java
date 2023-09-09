package com.ardaslegends.albaseplugin.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FactionStockpileModel {
    private String factionName;
    private int amount;

    public FactionStockpileModel(String factionName, int amount) {
        this.factionName = factionName;
        this.amount = amount;
    }

    public FactionStockpileModel() {

    }

    public String getFactionName() {
        return factionName;
    }

    public int getAmount() {
        return amount;
    }
}
