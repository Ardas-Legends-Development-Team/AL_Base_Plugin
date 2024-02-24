package com.ardaslegends.albaseplugin.inventory;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class FactionResources {

    public String factionName;
    private Inventory resources;

    FactionResources (String faction) {
        factionName = faction;
        List<ItemStack> resources;
    }
}
