package com.ardaslegends.albaseplugin.commands;

import com.ardaslegends.albaseplugin.AL_Base_Plugin;
import com.ardaslegends.albaseplugin.models.FactionModel;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class CommandLeaderActivity implements CommandExecutor {

    String msgPrefix = AL_Base_Plugin.getMsgPrefix();
    String errorPrefix = AL_Base_Plugin.getErrorPrefix();
    List<FactionModel> factions = AL_Base_Plugin.getFactions();

    /**
     * onCommand is being run, if the command leaderactivity is run
     * the commands syntax is
     * /leaderactivity {faction}
     *
     * @param sender Source of the command
     * @param command Command which was executed
     * @param label Alias of the command which was used
     * @param args Passed command arguments
     * @return if the syntax is correct, true is returned. On wrong syntax false
     */
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!AL_Base_Plugin.getBackendOnline()) {
            sender.sendMessage(errorPrefix + "The Backend is offline, this command requires the Backend to be online. Please contact the devs.");
            return true;
        }
        if(args.length == 0) {
            factions.forEach(factionModel -> {
                String leader = factionModel.getLeader();
                if (leader != null) {
                    sender.sendMessage(msgPrefix
                                       + "The Leader of "
                                       + factionModel.getName()
                                       + " is "
                                       + leader
                                       + " and was last seen:");
                    Bukkit.dispatchCommand(sender, "seen " + leader);
                }
            });
        } else if (args.length == 1) {
            FactionModel requestedFaction = new FactionModel(args[0].replace('_', ' '));
            if (factions.contains(requestedFaction)) {
                int requestedIndex = factions.indexOf(requestedFaction);
                String leader = factions.get(requestedIndex).getLeader();
                sender.sendMessage(msgPrefix
                                   + "The Leader of "
                                   + requestedFaction.getName()
                                   + " is "
                                   + leader
                                   + " and was last seen:");
                Bukkit.dispatchCommand(sender, "seen " + leader);
            }
        } else {
            sender.sendMessage(errorPrefix + "You have the wrong argument count.");
            return false;
        }
        return true;
    }
}
