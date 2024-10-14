package com.ardaslegends.albaseplugin.events;

import com.ardaslegends.albaseplugin.AL_Base_Plugin;
import com.ardaslegends.albaseplugin.alapiclients.PluginDBManager;
import com.ardaslegends.albaseplugin.models.DBModels.DBPlayerModel;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnPlayerJoinEvent implements Listener {

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        DBPlayerModel dbPlayerModel = new DBPlayerModel(player.getUniqueId().toString());
        PluginDBManager.persist(dbPlayerModel);
        player.sendMessage(AL_Base_Plugin.getMsgPrefix() + "You´ve been added to the database.");
    }
}
