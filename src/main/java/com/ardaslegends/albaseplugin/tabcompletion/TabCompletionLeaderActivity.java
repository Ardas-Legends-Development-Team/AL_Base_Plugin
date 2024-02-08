package com.ardaslegends.albaseplugin.tabcompletion;

import com.ardaslegends.albaseplugin.AL_Base_Plugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class TabCompletionLeaderActivity implements TabCompleter {
    /**
     * TabCompletion for the LeaderActivity Command
     * @param sender Source of the command
     * @param command Command which was executed
     * @param label Alias of the command which was used
     * @param args Passed command arguments
     * @return A List of Strings, that are the options for the player
     */
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (!AL_Base_Plugin.getBackendOnline()) {
            return null;
        }
        if (args.length == 1) {
            List<String> options = new ArrayList<>();
            AL_Base_Plugin.getFactions().forEach(faction -> options.add(faction.getName().replace(' ', '_')));
            return options;
        }

        return null;
    }
}
