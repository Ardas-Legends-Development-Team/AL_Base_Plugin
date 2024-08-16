package com.ardaslegends.albaseplugin.commands;

import com.ardaslegends.albaseplugin.AL_Base_Plugin;
import com.ardaslegends.albaseplugin.repository.HuntData;
import com.ardaslegends.albaseplugin.repository.HuntsManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandEndHunt implements CommandExecutor {
    /**
     * @param sender
     * @param command
     * @param label
     * @param args
     * @return
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Only Players can hunt");
            return false;
        }

        if(args.length == 0) {
            Player hunter = (Player) sender;
            int huntIndex = HuntsManager.isParticipating(hunter);
            if(huntIndex == -1) {
                sender.sendMessage(AL_Base_Plugin.PREFIX_HUNT_WARNING + " You must be in a hunt in order to cancel");
            } else {
                HuntData huntData = HuntsManager.getHunt(huntIndex);
                if(huntData.getAttackersUUID().contains(hunter.getUniqueId())) {
                    if(huntData.hunting()) {
                        HuntsManager.endHunt(huntIndex);
                        Bukkit.broadcastMessage(AL_Base_Plugin.PREFIX_HUNT + " " + ChatColor.RESET + hunter.getName() + ChatColor.RESET + " has cancelled the hunt!");
                    } else {
                        sender.sendMessage(AL_Base_Plugin.PREFIX_HUNT_WARNING + " Too late! The hunt has begun, now fight!");
                    }
                } else {
                    sender.sendMessage(AL_Base_Plugin.PREFIX_HUNT_WARNING + " Only the original hunter can cancel his hunt!");
                }
            }
            return true;
        } else if(args.length == 1) {
            if(!sender.hasPermission("al.hunt.end")) return false;
            String senderName = sender.getName();
            Player hunter = Bukkit.getPlayer(args[0]);
            if (hunter != null) {
                if (HuntsManager.canEndHunt(hunter)) {
                    HuntsManager.huntTerminated(hunter);
                    Bukkit.broadcastMessage(AL_Base_Plugin.PREFIX_HUNT + " " + ChatColor.DARK_RED + senderName + ChatColor.RESET + " has terminated the hunt prematurely!");
                } else {
                    sender.sendMessage(AL_Base_Plugin.PREFIX_HUNT_WARNING + " Couldn't find a hunt with the hunter player that initiated it");
                }
            } else {
                sender.sendMessage(AL_Base_Plugin.PREFIX_HUNT_WARNING + " Could not find that person online, make sure you typed his in game name correctly");
            }
        }
        return true;
    }
}
