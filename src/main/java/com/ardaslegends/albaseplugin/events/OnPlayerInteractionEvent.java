package com.ardaslegends.albaseplugin.events;

import com.ardaslegends.albaseplugin.repository.HuntData;
import com.ardaslegends.albaseplugin.repository.HuntsManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class OnPlayerInteractionEvent implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if(event.getClickedBlock() == null) return;
        Material blockType = event.getClickedBlock().getType();
        String blockName = blockType.name();

        int huntNumber = HuntsManager.isParticipating(player);
        if(huntNumber != -1) {
            HuntData hunt = HuntsManager.getHunt(huntNumber);
            if(hunt.isPlayerPresent(player)) {
                if(blockName.contains("chest") || blockName.contains("mallorn_box") || blockName.contains("forge") ||
                        blockName.contains("unsmeltery") || blockName.contains("millstone") || blockName.contains("barrel") ||
                        blockType == Material.FURNACE || blockType == Material.BURNING_FURNACE || blockType == Material.CHEST ||
                        blockType == Material.ENDER_CHEST || blockType == Material.HOPPER || blockType == Material.HOPPER_MINECART ||
                        blockType == Material.TRAPPED_CHEST || blockType == Material.ANVIL) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
