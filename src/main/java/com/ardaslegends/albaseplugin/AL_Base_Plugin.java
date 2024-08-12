package com.ardaslegends.albaseplugin;

import com.ardaslegends.albaseplugin.alapiclients.ALApiClient;
import com.ardaslegends.albaseplugin.commands.*;
import com.ardaslegends.albaseplugin.models.BackendModels.BackendFactionModel;
import com.ardaslegends.albaseplugin.resources.PredefinedResources;
import com.ardaslegends.albaseplugin.resources.Reloadables;
import com.ardaslegends.albaseplugin.resources.SafeFileManager;
import com.ardaslegends.albaseplugin.resources.StockpileConfig;
import com.ardaslegends.albaseplugin.tabcompletion.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

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
    private static final List<BackendFactionModel>     factions           = new ArrayList<>();
    private static boolean backendOnline;

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

        if (getConfig().contains("backend.online") && getConfig().getBoolean("backend.online")) {
            //Loading all Factions
            factions.addAll(setUpFactions());
        }

        //Setting up the reload functionality, which can not be disabled
        getCommand("alreload").setExecutor(new CommandALReload());
        getCommand("alreload").setTabCompleter(new TabCompletionALReload());

        //Setting up the util command, which can´t be disabled
        getCommand("alutil").setExecutor(new CommandUtility());
        getCommand("alutil").setTabCompleter(new TabCompletionUtility());

        //Setting up the leaderActivity Command, that we don´t need to toggle
        getCommand("leaderactivity").setExecutor(new CommandLeaderActivity());
        getCommand("leaderactivity").setTabCompleter(new TabCompletionLeaderActivity());

        //Setting up the stockpile feature if enabled
        if (getConfig().contains("feature.stockpile") && getConfig().getBoolean("feature.stockpile")) {
            //Registering the stockpile command
            getCommand("stockpile").setExecutor(new CommandStockpile());
            getCommand("stockpile").setTabCompleter(new TabCompletionStockpile());
            logger.log(Level.INFO, msgPrefix + "Feature activated: Stockpile");
        }

        //Setting up the rpchar feature if enabled
        if (getConfig().contains("feature.rpchar") && getConfig().getBoolean("feature.rpchar")) {
            //Registering the rpchar command
            getCommand("rpchar").setExecutor(new CommandRPChar());
            getCommand("rpchar").setTabCompleter(new TabCompletionRPChar());
            logger.log(Level.INFO, msgPrefix + "Feature activated: Roleplay Characters");
        }

        if (getConfig().contains("feature.monthly-resources") && getConfig().getBoolean("feature.monthly-resources")) {
            //Setting up the default resources in the files
            SafeFileManager.setUpFolderStructure();
            PredefinedResources.setUpResources();

            getCommand("refreshresources").setExecutor(new CommandRefreshResources());
            getCommand("refreshresources").setTabCompleter(new TabCompletionRefreshResources());
            logger.log(Level.INFO, msgPrefix + "Feature activated: Monthly Resources");
        }

        if (getConfig().contains("feature.hunting") && getConfig().getBoolean("feature.hunting")) {
            getCommand("hunt").setExecutor(new CommandHunt());
            getCommand("hunt").setTabCompleter(new TabCompletionHunt());

            getCommand("aid").setExecutor(new CommandAid());
            getCommand("aid").setTabCompleter(new TabCompletionAid());

            getCommand("endHunt").setExecutor(new CommandEndHunt());
            getCommand("endHunt").setTabCompleter(new TabCompletionEndHunt());
        }
    }

    /**
     * onDisable is being run, when the plugin is shut down.
     * It contains the shutdown logic for the plugin.
     * Information that might need to be saved should be saved within here etc.
     */
    @Override
    public void onDisable() {

    }

    /**
     * This is a temporary solution to autocomplete faction names in commands
     * Long term the factions will be loaded from the backend
     * @return A List of Faction Models containing all relevant factions of the server
     */
    private List<BackendFactionModel> setUpFactions(){
        List<BackendFactionModel> factions = apiClient.getFactions();
        factions.forEach(factionModel -> logger.log(Level.INFO, factionModel.getName()));
        return factions;
    }

    /**
     * reloading all config files
     */
    public void reload() {
        reloadConfig();
        StockpileConfig.reload();
        factions.addAll(setUpFactions());
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
                factions.addAll(setUpFactions());
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

    public static List<BackendFactionModel> getFactions() {
        return factions;
    }

    public static boolean getBackendOnline() {
        return backendOnline;
    }

    public static void setBackendOnline(boolean backendStatus) {
        backendOnline = backendStatus;
    }

}
