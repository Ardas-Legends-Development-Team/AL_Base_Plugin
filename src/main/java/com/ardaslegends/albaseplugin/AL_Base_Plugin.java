package com.ardaslegends.albaseplugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class AL_Base_Plugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        //setting up the configs
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        //Setting up the stockpile feature if enabled
        if (getConfig().contains("feature.stockpile")) {
            //TODO everything thats part of the stockpile feature
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
