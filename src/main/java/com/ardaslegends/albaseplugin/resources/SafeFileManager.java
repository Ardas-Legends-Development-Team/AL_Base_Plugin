package com.ardaslegends.albaseplugin.resources;

import com.ardaslegends.albaseplugin.models.SavefileModels.SafefileFactionModel;
import com.ardaslegends.albaseplugin.models.SavefileModels.SafefileResourceModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SafeFileManager {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final Logger logger = Bukkit.getServer().getLogger();
    private static final File dataFolder = Bukkit.getPluginManager().getPlugin("AL_Base_Plugin").getDataFolder();
    private static final File resources = new File(dataFolder, "Resources");
    private static final File predefinedResourceFolder = new File(resources, "Predefined_Resources");
    private static final File factionResourceFolder = new File(resources, "Factions");

    /**
     * This Method is setting up the FolderStructure for the ResourceFolder, with PredefinedResources and FactionResources
     */
    public static void setUpFolderStructure () {
        logger.log(Level.INFO, "Resource-Folder created: " + predefinedResourceFolder.mkdirs());
        logger.log(Level.INFO, "Factions-Folder created: " + factionResourceFolder.mkdirs());
    }

    /*
    public static SafefileResourceModel loadResource (String name) {
        SafefileResourceModel resource = new SafefileResourceModel(name);
        File resourceFile = new File(predefinedResourceFolder, name.replace(' ','_') + ".json");
        try {
            resource = mapper.readValue(resourceFile, SafefileResourceModel.class);
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        logger.log(Level.INFO, resource.toString());
        return resource;
    }
    */

    /**
     * This Method is used to load a predefined resources from the files using the name and productionSite
     *
     * @param name the name of the predefined resources as String
     * @param prodSite the production site type as a String
     * @return The predefined resources as SafefileResourceModel
     */
    public static SafefileResourceModel loadResource (String name, String prodSite) {
        SafefileResourceModel resource = new SafefileResourceModel(name);
        File resourceFile = new File(predefinedResourceFolder + "\\" + prodSite.replace(' ','_'), name.replace(' ','_') + ".json");
        try {
            resource = mapper.readValue(resourceFile, SafefileResourceModel.class);
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        logger.log(Level.INFO, resource.toString());
        return resource;
    }

    /*
    public static void safeResource (SafefileResourceModel resource) {
        File resourceFile = new File(predefinedResourceFolder.getAbsolutePath()
                + "\\"  + resource.getName().replace(' ','_') + ".json");
        if(!resourceFile.exists()){
            try {
                resourceFile.createNewFile();
                mapper.writeValue(resourceFile, resource);
            } catch (IOException e) {
                logger.log(Level.WARNING, e.getMessage());
            }
        } else {
            try {
                mapper.writeValue(resourceFile, resource);
            } catch (IOException e) {
                logger.log(Level.WARNING, e.getMessage());
            }
        }
    }
    */

    /**
     * This method saves the given ResourceModel as predefined Resource.
     * The path will be AL_Base_Plugin/Resources/Predefined_Resources/<Production Site>/<Resource>.json
     *
     * @param resource The Resource to be safed as predefined Resource
     * @param prodSite The Production Site the Resource comes from
     */
    public static void safeResource (SafefileResourceModel resource, String prodSite) {
        File resourceFile = new File(predefinedResourceFolder.getAbsolutePath()
                + "\\" + prodSite.replace(' ','_')
                + "\\"  + resource.getName().replace(' ','_') + ".json");
        if(!resourceFile.exists()){
            try {
                resourceFile.createNewFile();
                mapper.writeValue(resourceFile, resource);
            } catch (IOException e) {
                logger.log(Level.WARNING, e.getMessage());
            }
        }
    }

    /**
     * This Method is used to load a factions monthly resources from the files.
     * @param factionName the faction, of which the resources should be loaded
     * @return the factions resources as SafefileFactionModel
     */
    public static SafefileFactionModel loadFactionResources (String factionName) {
        SafefileFactionModel faction = new SafefileFactionModel(factionName);
        File factionFile = new File(factionResourceFolder.getAbsolutePath() + "\\" + factionName.replace(' ','_') + ".json");
        try {
            faction = mapper.readValue(factionFile, SafefileFactionModel.class);
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return faction;
    }

    /*
    public static void safeFactionResources (SafefileFactionModel factionModel) {
        File factionFile = new File(factionResourceFolder.getAbsolutePath() + "\\" + factionModel.getFactionName().replace(' ','_') + ".json");
        if(!factionFile.exists()){
            try {
                factionFile.createNewFile();
                mapper.writeValue(factionFile, factionModel);
            } catch (IOException e) {
                logger.log(Level.WARNING, e.getMessage());
            }
        } else {
            try {
                mapper.writeValue(factionFile, factionModel);
            } catch (IOException e) {
                logger.log(Level.WARNING, e.getMessage());
            }
        }
    }
    */

    /**
     * This Method is used to overwrite a FactionsResources, when they are updated or after they have been accessed by a player.
     * @param factionModel The FactionModel that represents the FactionResources
     */
    public static void overwriteFactionResources (SafefileFactionModel factionModel) {
        File factionFile = new File(factionResourceFolder.getAbsolutePath() + "\\" + factionModel.getFactionName().replace(' ','_') + ".json");
        if(!factionFile.exists()){
            try {
                factionFile.createNewFile();
                mapper.writeValue(factionFile, factionModel);
            } catch (IOException e) {
                logger.log(Level.WARNING, e.getMessage());
            }
        } else {
            try {
                factionFile.delete();
                factionFile.createNewFile();
                mapper.writeValue(factionFile, factionModel);
            } catch (IOException e) {
                logger.log(Level.WARNING, e.getMessage());
            }
        }
    }

    /*
     * Getter and Setter
     */
    public static File getPredefinedResourceFolder() {
        return predefinedResourceFolder;
    }
}
