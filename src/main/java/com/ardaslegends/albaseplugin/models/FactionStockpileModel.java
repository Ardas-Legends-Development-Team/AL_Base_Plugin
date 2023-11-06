package com.ardaslegends.albaseplugin.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This is a Model for a Stockpile.
 * This Model is used for the backend call of getting a FactionStockpile from the backend
 */
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
