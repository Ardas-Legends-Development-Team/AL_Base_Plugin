package com.ardaslegends.albaseplugin.commands;

import com.ardaslegends.albaseplugin.AL_Base_Plugin;
import com.ardaslegends.albaseplugin.resources.Reloadables;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandALReload implements CommandExecutor {

    String msgPrefix = AL_Base_Plugin.getMsgPrefix();
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
            if (!AL.Base_Plugin.getBackendOnline()) {
                sender.sendMessage(errorPrefix + "The Backend is offline, faction list could not be reloaded. Please contact the devs.");
                sender.sendMessage(msgPrefix + "All AL configs have successfully been reloaded.");
            }
            sender.sendMessage(msgPrefix + "Successfully reloaded all AL configs and the faction list.");
        } else if (args.length == 1) {
            String feature = args[0].toLowerCase();
            switch (feature) {
                case "base":
                    AL_Base_Plugin.getPlugin().reload(Reloadables.BASE);
                    sender.sendMessage(msgPrefix + "Successfully reloaded the base config.");
                    break;
                case "stockpile":
                    AL_Base_Plugin.getPlugin().reload(Reloadables.STOCKPILE);
                    sender.sendMessage(msgPrefix + "Successfully reloaded the stockpile config.");
                    break;
                case "factions":
                    AL_Base_Plugin.getPlugin().reload(Reloadables.FACTIONS);
                    if (!AL_Base_Plugin.getBackendOnline()) {
                        sender.sendMessage(errorPrefix + "The Backend is offline, faction list could not be reloaded. Please contact the devs.");
                        break;
                    }
                    sender.sendMessage(msgPrefix + "Successfully reloaded the faction list.");
                    break;
                default:
                    sender.sendMessage(errorPrefix + "Nothing to reload with that name.");
                    break;
            }
        } else {
            sender.sendMessage(errorPrefix + "Wrong argument count.");
            return false;
        }
        return true;
    }
}
