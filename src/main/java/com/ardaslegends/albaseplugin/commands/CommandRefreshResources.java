package com.ardaslegends.albaseplugin.commands;

import com.ardaslegends.albaseplugin.AL_Base_Plugin;
import com.ardaslegends.albaseplugin.inventory.FactionResources;
import com.ardaslegends.albaseplugin.models.FactionModel;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.PlayerInventory;

import java.util.List;

import static com.ardaslegends.albaseplugin.AL_Base_Plugin.getFactions;

public class CommandRefreshResources implements CommandExecutor {

    String msgPrefix = AL_Base_Plugin.getMsgPrefix();
    String errorPrefix = AL_Base_Plugin.getErrorPrefix();

    /**
     * The Command /refreshResources is used by staff to refresh the monthly resources
     * The Syntax for the command is
     * /refreshResources {faction}
     *
     * @param sender Source of the command
     * @param command Command which was executed
     * @param label Alias of the command which was used
     * @param args Passed command arguments
     * @return if the syntax is correct, true is returned. On wrong syntax false
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            //Refresh all Factions Monthly Resources
            List<FactionModel> factions = AL_Base_Plugin.getFactions();
            factions.forEach(factionModel -> refreshResourcesForFaction(factionModel.getName()) );
        } else if (args.length == 1){
            //Refresh just the Monthly Resources of the given Faction
            String faction = args[0].replace('_', ' ');
            refreshResourcesForFaction(faction);
        } else {
            sender.sendMessage(errorPrefix + "To many arguments given.");
            return false;
        }
        return true;
    }

    private void refreshResourcesForFaction(String factionName){
        FactionResources refreshedResources = new FactionResources(factionName);
        //Loading Resources from the backend and adding them to the list
    }
}