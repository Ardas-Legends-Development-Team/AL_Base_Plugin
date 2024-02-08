package com.ardaslegends.albaseplugin.tabcompletion;

import com.ardaslegends.albaseplugin.resources.Reloadables;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class TabCompletionALReload implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1) {
            List<String> options = new ArrayList<>();
            options.add(Reloadables.BASE.name());
            options.add(Reloadables.STOCKPILE.name());
            if (AL_Base_Plugin.getBackendOnline()) {
                options.add(Reloadables.FACTIONS.name());
            }
            return options;
        }
        return null;
    }
}
