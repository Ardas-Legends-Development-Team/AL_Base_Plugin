package com.ardaslegends.albaseplugin.resources;

import com.ardaslegends.albaseplugin.AL_Base_Plugin;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

/**
 * The StockpileConfig and all related things are defined in this class.
 * This class also defines the default values for the StockpileConfig
 */
public class StockpileConfig {

    private static File stockpile;
    private static       FileConfiguration stockpileConfig;
    private static final AL_Base_Plugin    al_base_plugin = AL_Base_Plugin.getPlugin();

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
        InputStream inputStream = al_base_plugin.getResource("stockpileConfig.yml");
        stockpileConfig =  YamlConfiguration.loadConfiguration(new InputStreamReader(inputStream));
        StockpileConfig.getStockpileConfig().options().copyDefaults(true);
        StockpileConfig.save();
    }
}
