package com.ardaslegends.albaseplugin.tabcompletion;

import com.ardaslegends.albaseplugin.repository.HuntsManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class TabCompletionHunt implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String label, String[] args) {

        if (args.length == 1) {
            List<String> options = new ArrayList<>();
            //Adding Players that are online and not in a hunt
            Bukkit.getServer().getOnlinePlayers().forEach(player -> {
                if (HuntsManager.isParticipating(player) == -1) {
                    options.add(player.getName());
                }
            });
            return options;
        }
        return null;
    }
}
