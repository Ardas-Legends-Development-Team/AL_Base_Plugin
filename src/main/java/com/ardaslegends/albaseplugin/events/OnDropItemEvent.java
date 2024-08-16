package com.ardaslegends.albaseplugin.events;

import com.ardaslegends.albaseplugin.repository.HuntData;
import com.ardaslegends.albaseplugin.repository.HuntsManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class OnDropItemEvent implements Listener {

    @EventHandler
    public void onDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        int huntNumber = HuntsManager.isParticipating(player);
        if(huntNumber != -1) {
            HuntData hunt = HuntsManager.getHunt(huntNumber);
            if(hunt.isPlayerPresent(player)) {
                event.setCancelled(true);
            }
        }
    }
}
