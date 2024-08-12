package com.ardaslegends.albaseplugin.repository;

import com.ardaslegends.albaseplugin.AL_Base_Plugin;
import com.ardaslegends.albaseplugin.util.Chronometer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class HuntData {
    private ArrayList<HuntParticipant> attackers;
    private ArrayList<HuntParticipant> defenders;
    private Chronometer chronometer = new Chronometer();
    private boolean terminate;
    private boolean preparation;

    public HuntData(UUID hunter, UUID hunted) {
        this.attackers = new ArrayList<>();
        this.defenders = new ArrayList<>();
        this.attackers.add(new HuntParticipant(Bukkit.getPlayer(hunter), true));
        this.defenders.add(new HuntParticipant(Bukkit.getPlayer(hunted), false));
        terminate = false;
        preparation = true; // Preparation

        chronometer.start();
        final int[] totalTime = {120};
        if(AL_Base_Plugin.getPlugin().getConfig().contains("hunting.hunt-prep-time")) {
            totalTime[0] = AL_Base_Plugin.getPlugin().getConfig().getInt("hunting.hunt-prep-time");
        }
        final ArrayList<Integer>[] countdown;
        if(AL_Base_Plugin.getPlugin().getConfig().contains("hunting.countdown")) {
            countdown = new ArrayList[1];
            countdown[0] = (ArrayList<Integer>) AL_Base_Plugin.getPlugin().getConfig().getIntegerList("hunting.countdown");
        } else {
            countdown = new ArrayList[]{new ArrayList<>(Arrays.asList(1,2,3,4,5))};
        }

        new BukkitRunnable() {
            @Override
            public void run () {
                if(terminate) {
                    terminate = false;
                    cancel();
                }

                if(chronometer.getTotalElapsedSeconds() <= totalTime[0]) {
                    long remainingSeconds = totalTime[0] - chronometer.getElapsedSeconds();
                    int timeIndex = countdown(remainingSeconds, countdown[0]);
                    if(timeIndex != -1) {
                        int timeLeft = countdown[0].get(timeIndex);
                        if(timeLeft <= 5) {
                            Bukkit.broadcastMessage(ChatConstants.PREFIX_HUNT + " " + ChatColor.BOLD + ChatColor.RED + remainingSeconds);
                        } else {
                            String status = "begins";
                            if(!preparation) status = "ends";
                            Bukkit.broadcastMessage(ChatConstants.PREFIX_HUNT + " Hunt " + status + " in "
                                    + ChatColor.BOLD + ChatColor.RED + timeLeft + ChatColor.RESET + " seconds");
                        }
                        countdown[0].remove(timeIndex);
                    }
                } else {
                    if(preparation) {
                        preparation = false;
                        chronometer.start();
                        countdown[0] = new ArrayList<>(countdown[0]);
                        totalTime[0] = HuntsManager.HUNT_TIME;
                        Bukkit.broadcastMessage(ChatConstants.PREFIX_HUNT + " May the hunt begin!");
                        String message = "";
                        message +=  ChatColor.GOLD + "---------" + ChatColor.WHITE + " [ List of Participants ] " + ChatColor.GOLD + "---------\n";
                        message +=  ChatColor.RED + "Hunters: §r" + "\n";
                        if(!attackers.isEmpty()) {
                            for(HuntParticipant attacker : attackers) {
                                message += " - " + attacker.name + "\n";
                            }
                        }
                        message +=  ChatColor.AQUA + "Hunted: §r" + "\n";
                        if(!defenders.isEmpty()) {
                            for(HuntParticipant defender : defenders) {
                                message += " - " + defender.name + "\n";
                            }
                        }
                        Bukkit.broadcastMessage(message);
                    } else {
                        int huntIndex = HuntsManager.getHunt(hunter);
                        HuntData huntData = HuntsManager.getHunt(huntIndex);
                        Bukkit.broadcastMessage(ChatConstants.PREFIX_HUNT + " The hunt has ended!");

                        String defenders = "";
                        int huntedSize = huntData.getDefendersParticipants().size();
                        for(int i = 0; i < huntedSize; i++) {
                            HuntParticipant participant = huntData.getDefendersParticipants().get(i);
                            defenders += ChatColor.AQUA + participant.name;
                            if(i < huntedSize - 1) defenders += ChatColor.WHITE + ", ";
                        }
                        String has = "has";
                        if(huntedSize > 1) has = "have";
                        Bukkit.broadcastMessage(ChatConstants.PREFIX_HUNT + " The hunted, " + defenders + ChatColor.RESET + ", " + has + " survived the hunt");
                        HuntsManager.sendScoreBoard(huntData);
                        chronometer.stop();
                        HuntsManager.endHunt(huntIndex);
                        cancel();
                    }
                }

            }
        }.runTaskTimer(AL_Base_Plugin.getPlugin(),0L, 2L);
    }

    public void terminate() {
        terminate = true;
    }

    public boolean hunting() {
        return !preparation;
    }

    public ArrayList<Player> getAttackers() {
        ArrayList<Player> players = new ArrayList<>();
        for(HuntParticipant participant : attackers) players.add(participant.getPlayer());
        return players;
    }

    public ArrayList<Player> getDefenders() {
        ArrayList<Player> players = new ArrayList<>();
        for(HuntParticipant participant : defenders) players.add(participant.getPlayer());
        return players;
    }

    public ArrayList<UUID> getAttackersUUID() {
        ArrayList<UUID> players = new ArrayList<>();
        for(HuntParticipant participant : attackers) players.add(participant.player);
        return players;
    }

    public ArrayList<UUID> getDefendersUUID() {
        ArrayList<UUID> players = new ArrayList<>();
        for(HuntParticipant participant : defenders) players.add(participant.player);
        return players;
    }

    public ArrayList<HuntParticipant> getAttackersParticipants() {
        return attackers;
    }

    public ArrayList<HuntParticipant> getDefendersParticipants() {
        return defenders;
    }

    public ArrayList<HuntParticipant> getParticipants() {
        ArrayList<HuntParticipant> participants = new ArrayList<HuntParticipant>();
        participants.addAll(defenders);
        participants.addAll(attackers);
        return participants;
    }

    public void addAttacker(Player player) {
        attackers.add(new HuntParticipant(player, true));
    }

    public void addDefender(Player player) {
        defenders.add(new HuntParticipant(player, false));
    }

    public boolean canPlayerFight(Player attacker, Player target) {
        if(isPlayerPresent(attacker)) { // Hunt participant can only attack when preparation time is over
            return !preparation;
        } else return !isPlayerPresent(target); // If you aren't a participant, you cannot interfere in the hunt
    }

    public boolean isPlayerPresent(Player player) {
        return (getAttackers().contains(player) || getDefenders().contains(player));
    }

    public HuntParticipant getParticipant(Player player) {
        for(HuntParticipant attacker : attackers) {
            if(attacker.getPlayer() == player) return attacker;
        }
        for(HuntParticipant defender : defenders) {
            if(defender.getPlayer() == player) return defender;
        }
        return null;
    }

    private int countdown(long remainingSeconds, List<Integer> countdown) {
        for(int i = 0; i < countdown.size(); i++) {
            if(countdown.get(i) >= remainingSeconds) return i;
        }

        return -1;
    }
}
