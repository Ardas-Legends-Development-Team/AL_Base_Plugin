package com.ardaslegends.albaseplugin.models.BackendModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This is a Model for a Stockpile.
 * This Model is used for the backend call of getting a FactionStockpile from the backend
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BackendFactionStockpileModel {
    private String factionName;
    private int amount;

    /*
     * Constructors
     */
    public BackendFactionStockpileModel(String factionName, int amount) {
        this.factionName = factionName;
        this.amount = amount;
    }

    public BackendFactionStockpileModel() {

    }

    /*
     * Getter and Setter
     */
    public String getFactionName() {
        return factionName;
    }

    public int getAmount() {
        return amount;
    }
}
