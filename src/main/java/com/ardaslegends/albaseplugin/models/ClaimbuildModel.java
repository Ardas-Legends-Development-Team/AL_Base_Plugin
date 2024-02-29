package com.ardaslegends.albaseplugin.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.hash.HashCode;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClaimbuildModel {
    private int id;
    private String name;
    private String faction;
    private List<ProductionSiteModel> productionSites;

    public ClaimbuildModel () {

    }

    public ClaimbuildModel (int id, String name, String faction, List<ProductionSiteModel> prodSites) {
        this.id = id;
        this.name = name;
        this.faction = faction;
        this.productionSites = prodSites;
    }

    public int getId() {
        return id;
    }

    public String getName () {
        return name;
    }

    public String getFaction () {
        return faction;
    }

    public void setFaction (String facName) {
        this.faction = facName;
    }

    public List<ProductionSiteModel> getProductionSites () {
        return productionSites;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            if (obj.getClass() == this.getClass()) {
                ClaimbuildModel otherCB = (ClaimbuildModel) obj;
                return otherCB.id == this.id && otherCB.name.equals(this.name);
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
