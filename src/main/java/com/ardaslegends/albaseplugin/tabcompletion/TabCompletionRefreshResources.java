package com.ardaslegends.albaseplugin.tabcompletion;

import com.ardaslegends.albaseplugin.AL_Base_Plugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class TabCompletionRefreshResources implements TabCompleter {
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
            List<String> options = new ArrayList<>();
            options.add("Angmar");
            options.add("Bree");
            options.add("Dale");
            options.add("Dol Amroth");
            options.add("Dol Guldur");
            options.add("Dorwinion");
            options.add("Dunland");
            options.add("Durins Folk");
            options.add("Ered Luin Dwarves");
            options.add("Gondor");
            options.add("Gulf of Harad");
            options.add("Gundabad");
            options.add("Half-Trolls");
            options.add("Harnennor");
            options.add("Hobbits");
            options.add("Isengard");
            options.add("Lindon");
            options.add("Lothlórien");
            options.add("Mordor");
            options.add("Morwaith");
            options.add("Nomads");
            options.add("Rangers");
            options.add("Rhúdel");
            options.add("Rivendell");
            options.add("Rohan");
            options.add("Southron Coast");
            options.add("Taurethrim");
            options.add("Umbar");
            options.add("Woodland Realm");
            return options;
        }
        if (args.length == 1) {
            List<String> options = new ArrayList<>();
            AL_Base_Plugin.getFactions().forEach(faction -> options.add(faction.getName().replace(' ', '_')));
            return options;
        }

        return null;
    }
}
