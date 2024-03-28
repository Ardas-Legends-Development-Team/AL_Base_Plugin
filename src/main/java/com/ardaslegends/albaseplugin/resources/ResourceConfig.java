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

public class ResourceConfig {

    private static File resourceConfigFile;
    private static FileConfiguration resourceConfig;
    private static final Logger logger = Bukkit.getServer().getLogger();
    private static final AL_Base_Plugin al_base_plugin = AL_Base_Plugin.getPlugin();

    /**
     * The method is setting up the Monthly Resources Configuration.
     * If the file already exists the config is just loaded into a variable
     * If the file does not exist, a new file is created and than the config is loaded
     */
    public static void setup(){
        resourceConfigFile = new File(Bukkit.getServer().getPluginManager().getPlugin(
                "AL_Base_Plugin").getDataFolder(), "resourceConfig.yml");
        if(!resourceConfigFile.exists()){
            try {
                resourceConfigFile.createNewFile();
            } catch (IOException e) {
                logger.log(Level.WARNING, "Could not create the file stockpileConfig.yml");
            }
        }
        resourceConfig = YamlConfiguration.loadConfiguration(resourceConfigFile);
    }

    /**
     * Saves the changes made to the configuration with commands/within the code into the actual config-file
     */
    public static void save(){
        try {
            resourceConfig.save(resourceConfigFile);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Could not save the file stockpileConfig.yml");
        }
    }

    /**
     * This method is used to reload the configuration from the file
     */
    public static void reload(){
        resourceConfig = YamlConfiguration.loadConfiguration(resourceConfigFile);
    }

    /**
     * This method is used to get the Resource-Configuration from another class
     * @return The FileConfiguration, that is the resourceConfig.yml loaded to
     * the plugin
     */
    public static FileConfiguration getResourceConfig(){
        return resourceConfig;
    }

    /**
     * Setting up the default values for resourceConfig.yml
     * The values given are the defaults, so if the resourceConfig.yml gets
     * deleted, we have values to regenerate it.
     * The default values consist of id and dataIdentifiers, which make up the items.
     */
    public static void addDefaults(){
        ResourceConfig.setup();
        InputStream inputStream = al_base_plugin.getResource("resourceConfig.yml");
        resourceConfig =  YamlConfiguration.loadConfiguration(new InputStreamReader(inputStream));
        ResourceConfig.getResourceConfig().options().copyDefaults(true);
        ResourceConfig.save();
    }
}
