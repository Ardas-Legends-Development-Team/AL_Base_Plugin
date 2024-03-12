package com.ardaslegends.albaseplugin.models.BackendModels;

/**
 * This is a Model for a ProductionSite.
 * This Model is used for the backend call of getting a ProductionSite from the backend
 */
public class BackendProductionSiteModel {
    int amount;
    BackendProductionSiteTypeModel productionSite;

    /*
     * Constructors
     */
    public BackendProductionSiteModel(int amount, BackendProductionSiteTypeModel productionSite) {
        this.amount = amount;
        this.productionSite = productionSite;
    }

    public BackendProductionSiteModel() {
    }

    /*
     * Getter and Setter
     */
    public int getAmount() {
        return amount;
    }

    public BackendProductionSiteTypeModel getProductionSite() {
        return productionSite;
    }
}
