package com.ardaslegends.albaseplugin.repository;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

public class HuntParticipant {
    public UUID player;
    public String name;
    public boolean alive;
    public float damageDealt;
    public float damageReceived;
    public int kills;
    public boolean hunter;
    public Location lastLocation;

    public HuntParticipant(Player participant, boolean hunter) {
        this.player = participant.getUniqueId();
        this.name = Bukkit.getPlayer(player).getName();
        this.hunter = hunter;
        this.alive = true;
        this.damageDealt = 0;
        this.damageReceived = 0;
        this.kills = 0;
        this.lastLocation = getPlayer().getLocation();
    }

    public boolean isOnline() {
        return Bukkit.getPlayer(player) != null;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(player);
    }
}
