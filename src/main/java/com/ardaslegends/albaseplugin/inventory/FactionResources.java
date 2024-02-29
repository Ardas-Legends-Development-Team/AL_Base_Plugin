package com.ardaslegends.albaseplugin.inventory;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class FactionResources {

    public String factionName;
    private Inventory resources;

    public FactionResources (String faction) {
        factionName = faction;
        List<ItemStack> resources;
    }

    public void addResource(ItemStack resource) {
        resources.addItem(resource);
    }

    public void clear () {
        resources.clear();
    }

    public void save () {
        //TODO: Saving the list to a file with the faction-name
    }

    public void load() {
        //TODO: Loading the list from a file with the faction-name
    }
}
