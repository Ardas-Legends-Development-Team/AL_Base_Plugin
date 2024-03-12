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

    public static void setUpFolderStructure () {
        logger.log(Level.INFO, "Resource-Folder created: " + predefinedResourceFolder.mkdirs());
        logger.log(Level.INFO, "Factions-Folder created: " + factionResourceFolder.mkdirs());
    }

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

    public static void safeResource (SafefileResourceModel resource) {
        File resourceFile = new File(predefinedResourceFolder.getAbsolutePath() + "\\"  + resource.getName().replace(' ','_') + ".json");
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
}
