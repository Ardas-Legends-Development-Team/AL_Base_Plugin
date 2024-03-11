package com.ardaslegends.albaseplugin.models.SavefileModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a Model for a Facton.
 * This Model is used to load a Faction from the Safefiles
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SafefileFactionModel {
    String factionName;
    String leaderIGN;
    List<SafefileRegionModel> regions;

    public SafefileFactionModel(String factionName) {
        this.factionName = factionName;
        regions = new ArrayList<>();
    }

    public SafefileFactionModel () {

    }

    public void addRegion(SafefileRegionModel region) {
        regions.add(region);
    }

    public String getFactionName() {
        return factionName;
    }

    public void setFactionName(String factionName) {
        this.factionName = factionName;
    }

    public String getLeaderIGN() {
        return leaderIGN;
    }

    public void setLeaderIGN(String leaderIGN) {
        this.leaderIGN = leaderIGN;
    }

    public List<SafefileRegionModel> getRegions() {
        return regions;
    }

    public void setRegions(List<SafefileRegionModel> regions) {
        this.regions = regions;
    }
}
