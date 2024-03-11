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
}
