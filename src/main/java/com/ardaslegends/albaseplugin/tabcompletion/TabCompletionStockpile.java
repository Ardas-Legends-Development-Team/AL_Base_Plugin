package com.ardaslegends.albaseplugin.tabcompletion;

import com.ardaslegends.albaseplugin.AL_Base_Plugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

/**
 * The possible tab-completion arguments are defined within this class.
 */
public class TabCompletionStockpile implements TabCompleter {

    /**
     * TabCompletion for the StockpileCommand
     * @param sender Source of the command
     * @param command Command which was executed
     * @param label Alias of the command which was used
     * @param args Passed command arguments
     * @return A List of Strings, that are the options for the player
     */
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1) {
            List<String> options = new ArrayList<>();
            options.add("add");
            options.add("info");
            options.add("stored");
            return options;
        } else if (args.length == 2 && args[0].equalsIgnoreCase("stored")) {
            List<String> options = new ArrayList<>();
            AL_Base_Plugin.getFactions().forEach(faction -> options.add(faction.getName().replace(' ', '_')));
            return options;
        } else if (args.length == 2 && args[0].equalsIgnoreCase("info")) {
            List<String> options = new ArrayList<>();
            options.add("Angmar");
            options.add("Bree");
            options.add("Dale");
            options.add("Dol_Amroth");
            options.add("Dol_Guldur");
            options.add("Dorwinion");
            options.add("Dunland");            
            options.add("Durins_Folk");
            options.add("Ered_Luin_Dwarves");
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

        return null;
    }
}
