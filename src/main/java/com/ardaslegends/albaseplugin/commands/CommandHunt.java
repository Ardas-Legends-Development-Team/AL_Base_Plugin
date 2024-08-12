package com.ardaslegends.albaseplugin.commands;

import com.ardaslegends.albaseplugin.repository.ChatConstants;
import com.ardaslegends.albaseplugin.repository.HuntsManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHunt implements CommandExecutor {
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
            sender.sendMessage(ChatConstants.PREFIX_HUNT_WARNING + " Please enter the player name that you wish to hunt like follow: /hunt Jqlo");
            sender.sendMessage(ChatConstants.PREFIX_HUNT_WARNING + " Make sure you're also 2000 blocks or closer to that target to start the hunt");
            return true;
        } else if(args.length == 1) {
            String senderName = sender.getName();
            Player hunter = Bukkit.getPlayer(senderName);
            Player hunted = Bukkit.getPlayer(args[0]);
            if (hunted != null) {
                if(HuntsManager.canHunt(hunter, hunted, sender)) {
                    HuntsManager.startHunt(hunter, hunted);
                }
            } else {
                sender.sendMessage(ChatConstants.PREFIX_HUNT_WARNING + " Could not find that person online, make sure you typed his in game name correctly");
            }
        }
        return true;
    }
}
