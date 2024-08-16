package com.ardaslegends.albaseplugin.events;

import com.ardaslegends.albaseplugin.repository.HuntData;
import com.ardaslegends.albaseplugin.repository.HuntsManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class OnPlayerEntityInteractionEvent implements Listener {

    @EventHandler
    public void onPlayerEntityInteract(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();
        EntityType entityType = entity.getType();

        int huntNumber = HuntsManager.isParticipating(player);
        if(huntNumber != -1) {
            HuntData hunt = HuntsManager.getHunt(huntNumber);
            if(hunt.isPlayerPresent(player)) {
                if(entityType == EntityType.MINECART_HOPPER || entityType == EntityType.MINECART_CHEST ||
                        entityType == EntityType.MINECART_FURNACE) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
