package com.ardaslegends.albaseplugin.tabcompletion;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class TabCompletionRPChar implements TabCompleter {

    /**
     * TabCompletion for the RPCharCommand
     * @param sender Source of the command
     * @param command Command which was executed
     * @param label Alias of the command which was used
     * @param args Passed command arguments
     * @return A List of Strings, that are the options for the player
     */
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 2) {
            List<String> options = new ArrayList<>();
            options.add("None");
            options.add("Mod");
            options.add("Admin");
            options.add("Dev");
            options.add("Owner");
            return options;
        }

        return null;
    }
}
