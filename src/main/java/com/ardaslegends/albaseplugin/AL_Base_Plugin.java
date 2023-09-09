package com.ardaslegends.albaseplugin;

import com.ardaslegends.albaseplugin.commands.CommandStockpile;
import com.ardaslegends.albaseplugin.resources.StockpileConfig;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class AL_Base_Plugin extends JavaPlugin {

    private static AL_Base_Plugin plugin;
    private static final String msgPrefix = ChatColor.GOLD + "[AL-Plugin] " + ChatColor.RESET;
    private static final String errorPrefix = ChatColor.DARK_RED + "[Error]" + ChatColor.RESET;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        //Setting up the config files
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        //Setting up the stockpile feature if enabled
        if (getConfig().contains("feature.stockpile")) {
            //Setting up the stockpileConfig.yml
            StockpileConfig.addDefaults();
            //Registering the stockpile command
            getCommand("stockpile").setExecutor(new CommandStockpile());
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /**
     * reloading all config files
     */
    public void reload() {
        reloadConfig();
    }

    public static AL_Base_Plugin getPlugin() {
        return plugin;
    }

    public static String getMsgPrefix() {
        return msgPrefix;
    }

    public static String getErrorPrefix(){
        return errorPrefix;
    }
}
