package com.ardaslegends.albaseplugin.tabcompletion;

import com.ardaslegends.albaseplugin.repository.HuntData;
import com.ardaslegends.albaseplugin.repository.HuntsManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TabCompletionAid implements TabCompleter {

    /**
     * @param commandSender
     * @param command
     * @param label
     * @param args
     * @return
     */
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String label, String[] args) {
        if (args.length == 1) {
            List<String> options = new ArrayList<>();
            for (HuntData hunt : HuntsManager.getHunts()) {
                for (Player attacker : hunt.getAttackers()) {
                    options.add(attacker.getName());
                }
                for (Player defender : hunt.getDefenders()) {
                    options.add(defender.getName());
                }
            }
            return options;
        }
        return null;
    }
}
