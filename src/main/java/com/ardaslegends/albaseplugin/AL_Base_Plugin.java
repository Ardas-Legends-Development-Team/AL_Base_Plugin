package com.ardaslegends.albaseplugin;

import com.ardaslegends.albaseplugin.commands.CommandRPChar;
import com.ardaslegends.albaseplugin.commands.CommandStockpile;
import com.ardaslegends.albaseplugin.models.FactionModel;
import com.ardaslegends.albaseplugin.resources.StockpileConfig;
import com.ardaslegends.albaseplugin.tabcompletion.TabCompletionStockpile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class AL_Base_Plugin extends JavaPlugin {

    private static       AL_Base_Plugin     plugin;
    private final        Logger             logger      = Bukkit.getServer().getLogger();
    private static final String             msgPrefix   = ChatColor.GOLD + "[AL-Plugin] " + ChatColor.RESET;
    private static final String             errorPrefix = ChatColor.DARK_RED + "[Error]" + ChatColor.RESET;
    private static final ALApiClient        apiClient   = new ALApiClient();
    private static final List<FactionModel> factions    = new ArrayList<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        //Setting up the config files
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        //Loading all Factions
        factions.addAll(apiClient.getFactions());
        logger.log(Level.INFO, factions.toString());

        //Setting up the stockpile feature if enabled
        if (getConfig().contains("feature.stockpile")) {
            //Setting up the stockpileConfig.yml
            StockpileConfig.addDefaults();
            //Registering the stockpile command
            getCommand("stockpile").setExecutor(new CommandStockpile());
            getCommand("stockpile").setTabCompleter(new TabCompletionStockpile());
        }

        //Setting up the rpchar feature if enabled
        if (getConfig().contains("feature.rpchar")) {
            //Registering the rpchar command
            getCommand("rpchar").setExecutor(new CommandRPChar());
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

    public static List<FactionModel> getFactions() {
        return factions;
    }
}
