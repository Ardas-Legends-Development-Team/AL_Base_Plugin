package com.ardaslegends.albaseplugin.tabcompletion;

import com.ardaslegends.albaseplugin.repository.HuntData;
import com.ardaslegends.albaseplugin.repository.HuntsManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TabCompletionEndHunt implements TabCompleter {

    /**
     * @param commandSender
     * @param command
     * @param label
     * @param args
     * @return
     */
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String label, String[] args) {
        if (args.length == 1 && commandSender.hasPermission("al.hunt.end")) {
            List<String> options = new ArrayList<>();
            for (HuntData hunt : HuntsManager.getHunts()) {
                options.add(hunt.getAttackers().get(0).getName());
                options.add(hunt.getDefenders().get(0).getName());
            }
            return options;
        }
        return null;
    }
}
