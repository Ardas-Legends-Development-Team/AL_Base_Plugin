package com.ardaslegends.albaseplugin;

import com.ardaslegends.albaseplugin.alapiclients.ALApiClient;
import com.ardaslegends.albaseplugin.commands.CommandRPChar;
import com.ardaslegends.albaseplugin.commands.CommandALReload;
import com.ardaslegends.albaseplugin.commands.CommandStockpile;
import com.ardaslegends.albaseplugin.models.FactionModel;
import com.ardaslegends.albaseplugin.resources.LastSeenJSON;
import com.ardaslegends.albaseplugin.resources.Reloadables;
import com.ardaslegends.albaseplugin.resources.StockpileConfig;
import com.ardaslegends.albaseplugin.tabcompletion.TabCompletionALReload;
import com.ardaslegends.albaseplugin.tabcompletion.TabCompletionStockpile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The main class of the plugin, this is where all things come together.
 */
public final class AL_Base_Plugin extends JavaPlugin {

    private static       AL_Base_Plugin     plugin;
    private static       ALApiClient apiClient;
    private static final Logger             logger      = Bukkit.getServer().getLogger();
    private static final String             msgPrefix   = ChatColor.GOLD + "[AL-Plugin] " + ChatColor.RESET;
    private static final String             errorPrefix = msgPrefix + ChatColor.DARK_RED + "[Error]" + ChatColor.RESET;
    private static final List<FactionModel>     factions           = new ArrayList<>();
    private static       HashMap<UUID, LocalDateTime> lastSeenFacLeaders = new HashMap<>();

    /**
     * onEnable is being run whenever the plugin is started.
     * It´s the entry point and thus the startup logic of the plugin.
     * In here all Commands and Events need to be registered to the server
     * and all Config files are to be set up here.
     * Static information, like the Faction List, should be loaded from the backend within the startup logic as well.
     */
    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        //Setting up the config files
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        //Setting up the stockpileConfig.yml
        StockpileConfig.addDefaults();

        //set up the ALApiClient
        apiClient = new ALApiClient();

        //Loading all Factions
        factions.addAll(setUpFactions());

        //Loading the lastSeen.json
        lastSeenFacLeaders = LastSeenJSON.load();

        //Setting up the reload functionality, which can not be disabled
        getCommand("alreload").setExecutor(new CommandALReload());
        getCommand("alreload").setTabCompleter(new TabCompletionALReload());

        //Setting up the stockpile feature if enabled
        if (getConfig().contains("feature.stockpile")) {
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

    /**
     * onDisable is being run, when the plugin is shut down.
     * It contains the shutdown logic for the plugin.
     * Information that might need to be saved should be saved within here etc.
     */
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        LastSeenJSON.save(lastSeenFacLeaders);
    }

    /**
     * This is a temporary solution to autocomplete faction names in commands
     * Long term the factions will be loaded from the backend
     * @return A List of Faction Models containing all relevant factions of the server
     */
    private List<FactionModel> setUpFactions(){
        List<FactionModel> factions = apiClient.getFactions();
        factions.forEach(factionModel -> logger.log(Level.INFO, factionModel.getName()));
        return factions;
    }

    private void saveLastSeen() {

    }

    /**
     * reloading all config files
     */
    public void reload() {
        reloadConfig();
        StockpileConfig.reload();
        setUpFactions();
    }

    /**
     * reloading a specific config, or the faction list, depending on the feature given
     * Possible features are:
     * - base: The base config of the plugin
     * - stockpile: The stockpileConfig
     * - factionList: The list of factions
     * @param feature the feature to be reloaded
     */
    public void reload(Reloadables feature) {
        switch (feature) {
            case BASE:
                reloadConfig();
                break;
            case STOCKPILE:
                StockpileConfig.reload();
                break;
            case FACTIONS:
                setUpFactions();
                break;
            default:
                break;
        }
    }

    public static AL_Base_Plugin getPlugin() {
        return plugin;
    }

    public static Logger getALLogger() {
        return logger;
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

    public static HashMap<UUID, LocalDateTime> getLastSeenFacLeaders() {
        return lastSeenFacLeaders;
    }
}
