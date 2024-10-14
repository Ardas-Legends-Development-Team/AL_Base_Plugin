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
import java.util.logging.Logger;

/**
 * The DepositConfig and all related things are defined in this class.
 * This class also defines the default values for the DepositConfig
 */
public class DepositConfig {

    private static File deposit;
    private static       FileConfiguration depositConfig;
    private static final Logger logger = Bukkit.getServer().getLogger();
    private static final AL_Base_Plugin    al_base_plugin = AL_Base_Plugin.getPlugin();

    /**
     * The method is setting up the FoodConfiguration.
     * If the file already exists the config is just loaded into a variable
     * If the file does not exist, a new file is created and than the config is loaded
     */
    public static void setup(){
        deposit = new File(Bukkit.getServer().getPluginManager().getPlugin(
                "AL_Base_Plugin").getDataFolder(), "depositConfig.yml");
        if(!deposit.exists()){
            try {
                deposit.createNewFile();
            } catch (IOException e) {
                logger.log(Level.WARNING, "Could not create the file depositConfig.yml");
            }
        }
        depositConfig = YamlConfiguration.loadConfiguration(deposit);
    }

    /**
     * Saves the changes made to the configuration with commands/within the code into the actual config-file
     */
    public static void save(){
        try {
            depositConfig.save(deposit);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Could not save the file depositConfig.yml");
        }
    }

    /**
     * This method is used to reload the configuration from the file
     */
    public static void reload(){
        depositConfig = YamlConfiguration.loadConfiguration(deposit);
    }

    /**
     * This method is used to get the Deposit-Configuration from another class
     * @return The FileConfiguration, that is the depositConfig.yml loaded to
     * the plugin
     */
    public static FileConfiguration getDepositConfig(){
        return depositConfig;
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
        DepositConfig.setup();
        InputStream inputStream = al_base_plugin.getResource("depositConfig.yml");
        depositConfig =  YamlConfiguration.loadConfiguration(new InputStreamReader(inputStream));
        DepositConfig.getDepositConfig().options().copyDefaults(true);
        DepositConfig.save();
    }
}
