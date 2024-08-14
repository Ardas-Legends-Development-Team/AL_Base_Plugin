package com.ardaslegends.albaseplugin.events;

import com.ardaslegends.albaseplugin.repository.ChatConstants;
import com.ardaslegends.albaseplugin.repository.HuntData;
import com.ardaslegends.albaseplugin.repository.HuntsManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnPlayerQuitEvent implements Listener {

    @EventHandler
    public void onDropItem(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        int huntNumber = HuntsManager.isParticipating(player);
        if(huntNumber != -1) {
            HuntData hunt = HuntsManager.getHunt(huntNumber);
            if(hunt.isPlayerPresent(player)) {
                player.setHealth(0);
                Bukkit.broadcastMessage(ChatConstants.PREFIX_HUNT + " " + player.getName() + " has combat logged during a hunt!");
                //Bukkit.broadcastMessage(player.getName() + " died");
                HuntsManager.participantDied(player);
            }
        }
    }
}
