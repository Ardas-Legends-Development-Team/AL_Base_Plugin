package com.ardaslegends.albaseplugin.commands;

import com.ardaslegends.albaseplugin.repository.ChatConstants;
import com.ardaslegends.albaseplugin.repository.HuntData;
import com.ardaslegends.albaseplugin.repository.HuntsManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandAid implements CommandExecutor {
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
            sender.sendMessage(ChatConstants.PREFIX_HUNT_WARNING + " Please enter the player name that you wish to aid like follow: /aid Jqlo");
            sender.sendMessage(ChatConstants.PREFIX_HUNT_WARNING + " Make sure you're also 2000 blocks or closer to that target to start the hunt");
            return true;
        } else if(args.length == 1) {
            String senderName = sender.getName();
            Player helper = Bukkit.getPlayer(senderName);
            Player helperTarget = Bukkit.getPlayer(args[0]);
            if (helperTarget != null) {
                int huntIndex = HuntsManager.isParticipating(helperTarget);
                if(huntIndex != -1) {
                    HuntData huntData = HuntsManager.getHunt(huntIndex);
                    if(!huntData.hunting()) {
                        if(HuntsManager.canAidPlayer(helper, helperTarget, sender)) {
                            HuntsManager.aidPlayer(helper, helperTarget);
                        }
                    } else {
                        sender.sendMessage(ChatConstants.PREFIX_HUNT_WARNING + " You're too late to come in help, the hunt has already begun");
                    }

                } else {
                    sender.sendMessage(ChatConstants.PREFIX_HUNT_WARNING + " " + helperTarget.getName() + " is not in a hunt currently");
                }
            } else {
                sender.sendMessage(ChatConstants.PREFIX_HUNT_WARNING + " Could not find that person online, make sure you typed his in game name correctly");
            }
        }
        return true;
    }
}
