package com.ardaslegends.albaseplugin.tabcompletion;

import com.ardaslegends.albaseplugin.AL_Base_Plugin;
import com.ardaslegends.albaseplugin.alapiclients.PluginDBManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class TabCompletionDeposit implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            List<String> options = new ArrayList<>();
            options.add("deposit");
            options.add("info");
            if (sender.hasPermission("al.staff.deposit.*")) {
                options.add("withdraw");
            }
            return options;
        } else if (args.length == 2 && sender.hasPermission("al.staff.deposit.*")) {
            return getFactionNames();
        }
        return null;
    }

    /**
     * Retrieves a list of faction names.
     *
     * @return a list of faction names. If the database is enabled, it retrieves the names from the database.
     *         Otherwise, it returns a predefined list of faction names.
     */
    private List<String> getFactionNames() {
        if (AL_Base_Plugin.isDatabaseEnabled()) {
            return PluginDBManager.getFactionNames();
        } else {
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
    }
}
