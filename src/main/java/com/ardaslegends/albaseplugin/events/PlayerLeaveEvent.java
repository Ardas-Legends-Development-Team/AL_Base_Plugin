package com.ardaslegends.albaseplugin.events;

import com.ardaslegends.albaseplugin.AL_Base_Plugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.time.LocalDateTime;

public class PlayerLeaveEvent implements Listener {
    
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        LocalDateTime now = LocalDateTime.now();
        Player player = event.getPlayer();

        if(AL_Base_Plugin.getLastSeenFacLeaders().containsKey(player.getUniqueId())) {
            AL_Base_Plugin.getLastSeenFacLeaders().replace(player.getUniqueId(), now);
        } else {
            AL_Base_Plugin.getLastSeenFacLeaders().put(player.getUniqueId(), now);
        }
    }
}