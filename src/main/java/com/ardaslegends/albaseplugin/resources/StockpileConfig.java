package com.ardaslegends.albaseplugin.resources;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class StockpileConfig {

    private static File stockpile;
    private static FileConfiguration stockpileConfig;

    /**
     * The method is setting up the FoodConfiguration.
     * If the file already exists the config is just loaded into a variable
     * If the file does not exist, a new file is created and than the config is loaded
     */
    public static void setup(){
        stockpile = new File(Bukkit.getServer().getPluginManager().getPlugin(
                "AL_Base_Plugin").getDataFolder(), "stockpileConfig.yml");
        if(!stockpile.exists()){
            try {
                stockpile.createNewFile();
            } catch (IOException e) {
                Bukkit.getServer().getLogger().log(Level.WARNING, "Could not "
                                                                  + "create "
                                                                  + "the file"
                                                                  + "stockpileConfig.yml");
            }
        }
        stockpileConfig = YamlConfiguration.loadConfiguration(stockpile);
    }

    /**
     * Saves the changes made to the configuration with commands/within the code into the actual config-file
     */
    public static void save(){
        try {
            stockpileConfig.save(stockpile);
        } catch (IOException e) {
            Bukkit.getServer().getLogger().log(Level.WARNING, "Could not save"
                                                              + " the file "
                                                              + "stockpileConfig.yml");
        }
    }

    /**
     * This method is used to reload the configuration from the file
     */
    public static void reload(){
        stockpileConfig = YamlConfiguration.loadConfiguration(stockpile);
    }

    /**
     * This method is used to get the Stockpile-Configuration from another class
     * @return The FileConfiguration, that is the stockpileConfig.yml loaded to
     * the plugin
     */
    public static FileConfiguration getStockpileConfig(){
        return stockpileConfig;
    }

    /**
     * Setting up the default values for foodsConfig.yml
     * The values given are the defaults, so if the foodsConfig.yml gets
     * deleted, we have values to regenerate it.
     * Default values for all accepted food is 1/64, so we end up with 1 point
     * for each stack of accepted food. This is done, so we are able to
     * adjust these values later down the road if necessary.
     */
    public static void addDefaults(){
        StockpileConfig.setup();
        //Setting up the values for each food item
        {
            StockpileConfig.getStockpileConfig().addDefault("BREAD", 0.015625);
            StockpileConfig.getStockpileConfig().addDefault("GRILLED_PORK", 0.015625);
            StockpileConfig.getStockpileConfig().addDefault("COOKED_BEEF", 0.015625);
            StockpileConfig.getStockpileConfig().addDefault("COOKED_CHICKEN", 0.015625);
            StockpileConfig.getStockpileConfig().addDefault("COOKED_FISH", 0.015625);
            StockpileConfig.getStockpileConfig().addDefault("COOKIES", 0.015625);
            StockpileConfig.getStockpileConfig().addDefault("LOTR_ITEMLEMBAS", 0.015625);
            StockpileConfig.getStockpileConfig().addDefault("LOTR_ITEMRABBITCOOKED", 0.015625);
            StockpileConfig.getStockpileConfig().addDefault("LOTR_ITEMGAMMON", 0.015625);
            StockpileConfig.getStockpileConfig().addDefault("LOTR_ITEMHOBBITPANCAKE", 0.015625);
            StockpileConfig.getStockpileConfig().addDefault("LOTR_ITEMRHINOCOOKED", 0.015625);
            StockpileConfig.getStockpileConfig().addDefault("LOTR_ITEMBANANABREAD", 0.015625);
            StockpileConfig.getStockpileConfig().addDefault("LOTR_ITEMHOBBITPANCAKEMAPLESIRUP", 0.015625);
            StockpileConfig.getStockpileConfig().addDefault("LOTR_ITEMCRAM", 0.015625);
            StockpileConfig.getStockpileConfig().addDefault("LOTR_ITEMMUTTONCOOKED", 0.015625);
            StockpileConfig.getStockpileConfig().addDefault("LOTR_ITEMMANFLESH", 0.015625);
            StockpileConfig.getStockpileConfig().addDefault("LOTR_ITEMCORNBREAD", 0.015625);
            StockpileConfig.getStockpileConfig().addDefault("LOTR_ITEMYAMROAST", 0.015625);
            StockpileConfig.getStockpileConfig().addDefault("LOTR_ITEMTURNIPCOOKED", 0.015625);
            StockpileConfig.getStockpileConfig().addDefault("LOTR_ITEMCAMELCOOKED", 0.015625);
            StockpileConfig.getStockpileConfig().addDefault("LOTR_ITEMSHISHKEBAB", 0.015625);
            StockpileConfig.getStockpileConfig().addDefault("LOTR_ITEMCORNCOOKED", 0.015625);
            StockpileConfig.getStockpileConfig().addDefault("LOTR_ITEMKEBAB", 0.015625);
            StockpileConfig.getStockpileConfig().addDefault("LOTR_ITEMDEERCOOKED", 0.015625);
            StockpileConfig.getStockpileConfig().addDefault("LOTR_ITEMZEBRACOOKED", 0.015625);
            StockpileConfig.getStockpileConfig().addDefault("LOTR_ITEMLIONCOOKED", 0.015625);
        }

        //Setting up restrictions
        {
            StockpileConfig.getStockpileConfig().addDefault("can-use-manflesh", getCanUseManflesh());
        }
        StockpileConfig.getStockpileConfig().options().copyDefaults(true);
        StockpileConfig.save();
    }

    /**
     * Defines a List of factions able to use Manflesh
     * @return a list of factions able to use Manflesh
     */
    private static List<String> getCanUseManflesh() {
        List<String> canUseManflesh = new ArrayList<>();
        canUseManflesh.add("Angmar");
        canUseManflesh.add("Dol Guldur");
        canUseManflesh.add("Dunland");
        canUseManflesh.add("Gulf of Harad");
        canUseManflesh.add("Half-Trolls");
        canUseManflesh.add("Harnennor");
        canUseManflesh.add("Isengard");
        canUseManflesh.add("Mordor");
        canUseManflesh.add("Morwaith");
        canUseManflesh.add("Nomads");
        canUseManflesh.add("Rhudel");
        canUseManflesh.add("Southron Coast");
        canUseManflesh.add("Umbar");
        return canUseManflesh;
    }
}
