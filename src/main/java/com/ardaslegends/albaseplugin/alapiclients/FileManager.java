package com.ardaslegends.albaseplugin.alapiclients;

import org.bukkit.Bukkit;

import java.io.File;

public class FileManager {

    File file;

    private static final File dataFolder = Bukkit.getPluginManager().getPlugin("AL_Base_Plugin").getDataFolder();

    public FileManager (String filename) {
        this.file = new File(dataFolder, filename);
    }

    public void save () {

    }
}
