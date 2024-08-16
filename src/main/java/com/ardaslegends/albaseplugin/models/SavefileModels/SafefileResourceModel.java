package com.ardaslegends.albaseplugin.models.SavefileModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * This is a Model for a Resource.
 * This Model is used to load a Resource from the Safefiles
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SafefileResourceModel {
    String name;
    int minecraftID;
    int data;
    int amount;
    String lotrEnchant;

    /*
     * Constructors
     */
    public SafefileResourceModel (String name, int mcID, int data, int amount, String lotrEnchant) {
        this.name = name;
        this.minecraftID = mcID;
        this.data = data;
        this.amount = amount;
        this.lotrEnchant = lotrEnchant;
    }
    public SafefileResourceModel (String name) {
        this.name = name;
    }

    public SafefileResourceModel() {
    }

    /*
     * Getter and Setter
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinecraftID() {
        return minecraftID;
    }

    public void setMinecraftID(int minecraftID) {
        this.minecraftID = minecraftID;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getLotrEnchant() {
        return lotrEnchant;
    }

    public void setLotrEnchant(String lotrEnchant) {
        this.lotrEnchant = lotrEnchant;
    }
}
