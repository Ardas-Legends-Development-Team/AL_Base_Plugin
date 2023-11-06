package com.ardaslegends.albaseplugin.resources;

import com.ardaslegends.albaseplugin.AL_Base_Plugin;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;

public class LastSeenJSON {
    private static File lastSeenJSON = new File(Bukkit.getServer().getPluginManager().getPlugin(
            "AL_Base_Plugin").getDataFolder(), "lastSeen.json");

    private static ObjectMapper mapper = new ObjectMapper();

    public static void save(HashMap<UUID, LocalDateTime> lastSeen){
        try {
            if (lastSeenJSON.exists()) {
                mapper.writeValue(lastSeenJSON, lastSeen);
            } else {
                lastSeenJSON.createNewFile();
                mapper.writeValue(lastSeenJSON, lastSeen);
            }
        } catch (IOException e) {
            AL_Base_Plugin.getALLogger().log(Level.WARNING, AL_Base_Plugin.getErrorPrefix() + "Error saving the file lastSeen.json");
        }
    }

    public static HashMap<UUID, LocalDateTime> load(){
        HashMap<UUID, LocalDateTime> lastSeenMap = new HashMap<>();
        try {
            lastSeenMap = mapper.readValue(lastSeenJSON, new TypeReference<HashMap<UUID, LocalDateTime>>() {});
        } catch (IOException e) {
            AL_Base_Plugin.getALLogger().log(Level.WARNING, AL_Base_Plugin.getErrorPrefix() + "Error loading the file lastSeen.json");
        }
        return lastSeenMap;
    }
}
