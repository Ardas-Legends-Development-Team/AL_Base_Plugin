package com.ardaslegends.albaseplugin.events;

import com.ardaslegends.albaseplugin.AL_Base_Plugin;
import com.ardaslegends.albaseplugin.repository.HuntData;
import com.ardaslegends.albaseplugin.repository.HuntParticipant;
import com.ardaslegends.albaseplugin.repository.HuntsManager;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class OnAttackEvent implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if(event.getDamager().getType() == EntityType.PLAYER && event.getEntityType() == EntityType.PLAYER) {
            Player attacker = (Player) event.getDamager();
            Player victim = (Player) event.getEntity();
            int huntNumber = HuntsManager.isParticipating(attacker);
            if(huntNumber == -1) huntNumber = HuntsManager.isParticipating(victim);

            if(huntNumber != -1) {
                HuntData hunt = HuntsManager.getHunt(huntNumber);
                if(!hunt.canPlayerFight(attacker, victim)) {
                    event.setCancelled(true);
                } else {
                    HuntParticipant attackerPlayer = hunt.getParticipant(attacker.getPlayer());
                    HuntParticipant victimPlayer = hunt.getParticipant(victim.getPlayer());

                    if(attackerPlayer.alive && victimPlayer.alive) {
                        attackerPlayer.damageDealt += (float) event.getDamage();
                        victimPlayer.damageReceived += (float) event.getDamage();
                        if(victim.getHealth() - event.getDamage() <= 0) {
                            attackerPlayer.kills++;
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    HuntsManager.participantDied(victim);
                                }
                            }.runTaskLater(AL_Base_Plugin.getPlugin(),1L); // We announce death after the message of death in chat

                        }
                    } else {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}
