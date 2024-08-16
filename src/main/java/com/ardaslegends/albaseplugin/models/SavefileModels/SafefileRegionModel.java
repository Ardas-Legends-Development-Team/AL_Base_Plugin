package com.ardaslegends.albaseplugin.models.SavefileModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a Model for a Region.
 * This Model is used to load a Region from the Safefiles
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SafefileRegionModel {
    int regionNumber;
    String regionName;
    String regionType;
    List<SafefileClaimbuildModel> claimbuilds;

    public SafefileRegionModel(int regionNumber) {
        this.regionNumber = regionNumber;
        claimbuilds = new ArrayList<>();
    }

    public SafefileRegionModel () {
        claimbuilds = new ArrayList<>();
    }

    public void addClaimbuild (SafefileClaimbuildModel claimbuild) {
        claimbuilds.add(claimbuild);
    }

    public int getRegionNumber() {
        return regionNumber;
    }

    public void setRegionNumber(int regionNumber) {
        this.regionNumber = regionNumber;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public List<SafefileClaimbuildModel> getClaimbuilds() {
        return claimbuilds;
    }

    public void setClaimbuilds(List<SafefileClaimbuildModel> claimbuilds) {
        this.claimbuilds = claimbuilds;
    }

    public String getRegionType() {
        return regionType;
    }

    public void setRegionType(String region_type) {
        this.regionType = region_type;
    }
}
