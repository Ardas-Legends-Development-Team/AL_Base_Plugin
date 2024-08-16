package com.ardaslegends.albaseplugin.repository;

import java.util.UUID;

public class CooldownPlayer {
    public UUID player;
    public long lastHuntTime;

    public CooldownPlayer(UUID player, long lastHuntTime) {
        this.player = player;
        this.lastHuntTime = lastHuntTime;
    }

    public float getRemainingSeconds() {
        return HuntsManager.HUNTER_COOLDOWN - (float) ((System.currentTimeMillis() - lastHuntTime) / 1000);
    }
}