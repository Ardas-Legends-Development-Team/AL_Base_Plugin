package com.ardaslegends.albaseplugin.commands;

import com.ardaslegends.albaseplugin.AL_Base_Plugin;
import com.ardaslegends.albaseplugin.resources.Reloadables;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandALReload implements CommandExecutor {

    String errorPrefix = AL_Base_Plugin.getErrorPrefix();

    /**
     * The reload command, used to reload either all reloadables or a specific reloadable
     * @param sender Source of the command
     * @param command Command which was executed
     * @param label Alias of the command which was used
     * @param args Passed command arguments
     * @return if the syntax is correct, true is returned. On wrong syntax false
     */
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            AL_Base_Plugin.getPlugin().reload();
        } else if (args.length == 1) {
            String feature = args[0].toLowerCase();
            switch (feature) {
                case "base":
                    AL_Base_Plugin.getPlugin().reload(Reloadables.BASE);
                case "stockpile":
                    AL_Base_Plugin.getPlugin().reload(Reloadables.STOCKPILE);
                case "factions":
                    AL_Base_Plugin.getPlugin().reload(Reloadables.FACTIONS);
                default:
                    sender.sendMessage(errorPrefix + "Nothing to reload with that name.");
            }
        } else {
            sender.sendMessage(errorPrefix + "Wrong argument count.");
            return false;
        }
        return true;
    }
}
