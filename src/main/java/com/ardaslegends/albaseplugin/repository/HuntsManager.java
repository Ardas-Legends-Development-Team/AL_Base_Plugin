package com.ardaslegends.albaseplugin.repository;

import com.ardaslegends.albaseplugin.AL_Base_Plugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class HuntsManager {
    private static ArrayList<HuntData> hunts;
    private static ArrayList<CooldownPlayer> cooldownPlayers;
    public static int TRAVEL_DISTANCE = 200;
    public static int PREP_TIME = 120;
    public static ArrayList<Integer> COUNTDOWN = new ArrayList<>(Arrays.asList(1,2,3,4,5,10));
    public static int HUNT_TIME = 600;
    public static int HUNTER_COOLDOWN = 600;

    public static void init() {
        hunts = new ArrayList<>();
        cooldownPlayers = new ArrayList<>();
        if(AL_Base_Plugin.getPlugin().getConfig().contains("hunting.travel-distance")) {
            TRAVEL_DISTANCE = AL_Base_Plugin.getPlugin().getConfig().getInt("hunting.travel-distance");
        }
        if(AL_Base_Plugin.getPlugin().getConfig().contains("hunting.hunt-prep-time")) {
            PREP_TIME = AL_Base_Plugin.getPlugin().getConfig().getInt("hunting.hunt-prep-time");
        }
        if(AL_Base_Plugin.getPlugin().getConfig().contains("hunting.countdown")) {
            COUNTDOWN = (ArrayList<Integer>) AL_Base_Plugin.getPlugin().getConfig().getIntegerList("hunting.countdown");
        }
        if(AL_Base_Plugin.getPlugin().getConfig().contains("hunting.hunt-time")) {
            HUNT_TIME = AL_Base_Plugin.getPlugin().getConfig().getInt("hunting.hunt-time");
        }
        if(AL_Base_Plugin.getPlugin().getConfig().contains("hunting.hunter-cooldown")) {
            HUNTER_COOLDOWN = AL_Base_Plugin.getPlugin().getConfig().getInt("hunting.hunter-cooldown");
        }
    }

    public static void tick() {
        cooldownPlayers.removeIf(cooldownPlayer -> cooldownPlayer.getRemainingSeconds() <= 0);

        for(HuntData huntData : hunts) {
            for(HuntParticipant participant : huntData.getParticipants()) {
                Player player = Bukkit.getPlayer(participant.player);
                if(player != null) {
                    float traveledDistance = (float) participant.lastLocation.distanceSquared(player.getLocation());
                    if(traveledDistance > TRAVEL_DISTANCE) {
                        player.teleport(participant.lastLocation);
                        participantTriesTeleports(player);
                    }
                    participant.lastLocation = player.getLocation();
                }
            }
        }
    }

    public static void participantTriesTeleports(Player player) {
        Bukkit.broadcastMessage(AL_Base_Plugin.PREFIX_HUNT + " " + player.getName() + ChatColor.RED + " tried to teleport during a hunt! Teleporting back the player to his old position");
    }

    public static boolean canHunt(Player hunter, Player hunted, CommandSender sender) {
        if(hunted.getUniqueId() == hunter.getUniqueId()) {
            sender.sendMessage(AL_Base_Plugin.PREFIX_HUNT_WARNING + " You cannot hunt yourself!");
            return false;
        } else if(HuntsManager.isParticipating(hunter) != -1) {
            sender.sendMessage(AL_Base_Plugin.PREFIX_HUNT_WARNING + " You are already in a hunt!");
            return false;
        }

        UUID hunterID = hunter.getUniqueId();
        for (HuntData hunt : hunts) {
            if(hunt.getAttackersUUID().contains(hunter.getUniqueId())) {
                sender.sendMessage(AL_Base_Plugin.PREFIX_HUNT_WARNING + " You cannot hunt this hunter since he's already in a hunt.\n " +
                        "You can join by typing " + ChatColor.ITALIC + ChatColor.GRAY + "/aid [HuntedName]");
                return false;
            } else if(hunt.getDefendersUUID().contains(hunter.getUniqueId())) {
                sender.sendMessage(AL_Base_Plugin.PREFIX_HUNT_WARNING + " You cannot hunt this hunted player since he's already in a hunt.\n " +
                        "You can join by typing " + ChatColor.ITALIC + ChatColor.GRAY + "/aid [HunterName]");
                return false;
            }
        }

        if(hunter.getLocation().distanceSquared(hunted.getLocation()) > 2000) {
            sender.sendMessage(AL_Base_Plugin.PREFIX_HUNT_WARNING + " You cannot hunt this player since he's further than 2000 blocks from you.");
            return false;
        } else {
            for(CooldownPlayer cooldownPlayer : cooldownPlayers) {
                if(cooldownPlayer.player.equals(hunterID)) {
                    float timeRemaining = cooldownPlayer.getRemainingSeconds();
                    sender.sendMessage(AL_Base_Plugin.PREFIX_HUNT_WARNING + " You must wait " + (int)timeRemaining + " seconds before hunting again");
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean startHunt(Player hunter, Player hunted) {
        hunts.add(new HuntData(hunter.getUniqueId(), hunted.getUniqueId()));

        Bukkit.broadcastMessage(AL_Base_Plugin.PREFIX_HUNT + ChatColor.BOLD + " " + ChatColor.RED + hunter.getName()
                + ChatColor.RESET + " is starting soon a hunt against " + ChatColor.BOLD + ChatColor.AQUA + hunted.getName());
        Bukkit.broadcastMessage(AL_Base_Plugin.PREFIX_HUNT + ChatColor.RESET + " You can help either the hunter or hunted by typing: "
                + ChatColor.ITALIC + ChatColor.GRAY + "/aid [PlayerName]" + ChatColor.RESET + " command during the 2 minutes of preparation");

        return true;
    }

    public static boolean canAidPlayer(Player helper, Player target, CommandSender sender) {
        if(helper.getUniqueId() == target.getUniqueId()) {
            sender.sendMessage(AL_Base_Plugin.PREFIX_HUNT_WARNING + " You cannot help yourself!");
            return false;
        } else if(HuntsManager.isParticipating(helper) != -1) {
            sender.sendMessage(AL_Base_Plugin.PREFIX_HUNT_WARNING + " You are already in a hunt!");
            return false;
        } else if(helper.getLocation().distanceSquared(target.getLocation()) > 2000) {
            sender.sendMessage(AL_Base_Plugin.PREFIX_HUNT_WARNING + " You cannot help this player since he's further than 2000 blocks from you.");
            return false;
        } else {
            for(CooldownPlayer cooldownPlayer : cooldownPlayers) {
                if(cooldownPlayer.player.equals(helper.getUniqueId())) {
                    float timeRemaining = cooldownPlayer.getRemainingSeconds();
                    sender.sendMessage(AL_Base_Plugin.PREFIX_HUNT_WARNING + " You must wait " + (int)timeRemaining + " seconds before entering a hunt again");
                    return false;
                }
            }
        }

        return true;
    }

    public static void aidPlayer(Player helper, Player target) {
        int huntIndex = HuntsManager.isParticipating(target);
        if(huntIndex != -1) {
            HuntData huntData = HuntsManager.getHunt(huntIndex);
            HuntParticipant targetParticipant = huntData.getParticipant(target);
            String teamColor = "" + ChatColor.AQUA;
            if(targetParticipant.hunter) {
                teamColor = "" + ChatColor.RED;
                huntData.addAttacker(helper);
            } else {
                huntData.addDefender(helper);
            }

            Bukkit.broadcastMessage(AL_Base_Plugin.PREFIX_HUNT + ChatColor.BOLD + " " + teamColor + helper.getName()
                    + ChatColor.RESET + " is helping " + ChatColor.BOLD + "" + teamColor + target.getName() + " in the hunt!");
        }
    }

    public static boolean canEndHunt(Player hunter) {
        UUID hunterID = hunter.getUniqueId();
        for (HuntData hunt : hunts) {
            if (hunt.getAttackersUUID().get(0) == hunterID) {
                return true;
            }
        }
        return false;
    }

    public static void huntTerminated(Player hunter) {
        UUID hunterID = hunter.getUniqueId();
        for (int i = 0; i < hunts.size(); i++) {
            if(hunts.get(i).getAttackersUUID().contains(hunterID)) {
                hunts.get(i).terminate();
                hunts.remove(i);
                return;
            }
        }
    }

    public static void endHunt(int index) {
        HuntData huntData = hunts.get(index);
        long now = System.currentTimeMillis();
        for(UUID attacker : huntData.getAttackersUUID()) {
            cooldownPlayers.add(new CooldownPlayer(attacker, now));
        }
        huntData.terminate();
        hunts.remove(index);
    }

    public static void participantDied(Player deadPlayer) {
        for (int i = 0; i < hunts.size(); i++) {
            HuntData hunt = hunts.get(i);
            if(hunt.isPlayerPresent(deadPlayer)) {
                HuntParticipant deadParticipant = hunt.getParticipant(deadPlayer);
                deadParticipant.alive = false;

                HuntStateEnum state = haveWon(deadParticipant, hunt);
                if(state == HuntStateEnum.DEFENDERS_WIN || state == HuntStateEnum.HUNTERS_WIN) {
                    String team = ChatColor.RED + "hunter(s)";
                    if(state == HuntStateEnum.DEFENDERS_WIN) team = ChatColor.AQUA + "hunted";
                    Bukkit.broadcastMessage(AL_Base_Plugin.PREFIX_HUNT + ChatColor.RESET + " Congratulation to the " +
                            team + ChatColor.RESET + "! You may loot their death chest(s)");
                    sendScoreBoard(hunt);
                    endHunt(i);
                    return;
                }
            }
        }
    }

    public static void sendScoreBoard(HuntData hunt) {
        String message = "";
        message +=  ChatColor.GOLD + "---------" + ChatColor.WHITE + " [ Scoreboard ] " + ChatColor.GOLD + "---------\n";
        for(HuntParticipant participant : hunt.getParticipants()) {
            String playerPrefix = getTeamColor(participant);
            message +=  playerPrefix + participant.getPlayer().getName() + ChatColor.RESET +
                    ChatColor.GRAY + " -> " + ChatColor.RESET + "Kills: " + participant.kills + ", Damage Dealt: " + participant.damageDealt +
                    ", Damage Received: " + participant.damageReceived + "\n";
        }
        Bukkit.broadcastMessage(message);
    }

    private static String getTeamColor(HuntParticipant player) {
        String teamColor = "" + ChatColor.AQUA;
        if(player.hunter) {
            teamColor = "" + ChatColor.RED;
        }
        return teamColor;
    }

    private static HuntStateEnum haveWon(HuntParticipant deadParticipant, HuntData hunt) {
        HuntStateEnum stateEnum = HuntStateEnum.HUNTING;
        if(deadParticipant.hunter) {
            boolean someoneAlive = false;
            for(HuntParticipant hunter : hunt.getAttackersParticipants()) {
                if(hunter.alive)  {
                    someoneAlive = true;
                    break;
                }
            }
            if(!someoneAlive) stateEnum = HuntStateEnum.DEFENDERS_WIN;
        } else {
            boolean someoneAlive = false;
            for(HuntParticipant hunter : hunt.getAttackersParticipants()) {
                if(hunter.alive)  {
                    someoneAlive = true;
                    break;
                }
            }
            if(!someoneAlive) stateEnum = HuntStateEnum.HUNTERS_WIN;
        }
        return stateEnum;
    }

    public static int isParticipating(Player player) {
        /*UUID playerID = player.getUniqueId();
        for (int i = 0; i < hunts.size(); i++) {
            if(hunts.get(i).getAttackersUUID().contains(playerID)) {
                return i;
            } else if(hunts.get(i).getDefendersUUID().contains(playerID)) {
                return i;
            }
        }
        return -1;*/
        return getHunt(player.getUniqueId());
    }

    public static int getHunt(UUID uuid) {
        if (hunts != null) {
            for (int i = 0; i < hunts.size(); i++) {
                if(hunts.get(i).getAttackersUUID().contains(uuid)) {
                    return i;
                } else if(hunts.get(i).getDefendersUUID().contains(uuid)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static HuntData getHunt(int index) {
        return hunts.get(index);
    }

    public static ArrayList<HuntData> getHunts() {
        return hunts;
    }
}
