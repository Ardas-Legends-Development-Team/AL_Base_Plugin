package com.ardaslegends.albaseplugin.commands;

import com.ardaslegends.albaseplugin.AL_Base_Plugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * All Methods and variables related to the Command RPChar are within this class
 */
public class CommandRPChar implements CommandExecutor {

    String msgPrefix = AL_Base_Plugin.getMsgPrefix();
    String errorPrefix = AL_Base_Plugin.getErrorPrefix();

    /**
     * onCommand is being run if the command rpchar was executed
     * the syntax of the cmd is
     * - /rpchar [ign] [character] [pvp] [leader] [staff] {title}
     *
     * @param sender Source of the command
     * @param command Command which was executed
     * @param label Alias of the command which was used
     * @param args Passed command arguments
     * @return if the syntax is correct, true is returned. On wrong syntax false
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //If the argument count is too low
        // (We need at least 5 arguments and can have many more as each word of the title is one argument)
        if (args.length < 5) {
            sender.sendMessage(errorPrefix + "You have given to few arguments.");
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);

        //If the target is not found inform the sender and end the command
        if (target == null) {
            sender.sendMessage(msgPrefix + "Could not find the Player " + args[0] + ".");
            return true;
        }

        //Setting up the required arguments for the command
        String charname = args[1].replace('_', ' ');
        boolean pvp = args[2].equalsIgnoreCase("pvp");
        boolean leader = args[3].equalsIgnoreCase("yes");
        String staffRole = args[4];

        //Setting up the title for the player
        StringBuilder titleSb = new StringBuilder();
        if (args.length > 5) {
            for (int i = 5; i < args.length; i++) {
                titleSb.append(args[i])
                       .append(" ");
            }
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                                   "manuaddv " + target.getName() + " prefix "
                                   + "[" + titleSb.toString() + "&r]");
            sender.sendMessage(msgPrefix + "The new title for " + target.getName() +  " "
                              + "is " + titleSb.toString());
        }

        //Setting up the players nickname to match the character name
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "nick " + target.getName() + " " + charname);
        sender.sendMessage(msgPrefix + "The nickname of " + target.getName() + " is now " + charname);

        //Setting up the permissions of the player
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                               "manuaddsub " + target.getName()
                               + " RPChar");
        sender.sendMessage(msgPrefix + target.getName() + " was "
                          + "successfully given "
                          + "RPChar-Permissions");
        if (pvp) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                                   "manuaddsub " + target.getName() + " PvP");
            sender.sendMessage(msgPrefix + "PvP has been added to " + target.getName());
        }

        //Building the target Players suffix and setting it
        String suffix = buildSuffix(pvp, leader, staffRole);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                               "manuaddv " + target.getName() + " suffix " + suffix);
        sender.sendMessage(msgPrefix + "The Players new Suffix is: " + suffix);

        return true;
    }

    /**
     * This Method builds a string containing the suffix to be used.
     * @param pvp boolean if the target has PvP or PvE
     * @param leader boolean if the target is a faction Leader
     * @param staffRole String that contains the staff role of the player {
     *                  "Mod","Admin","Dev","Owner"}
     * @return a String containing the constructed suffix
     */
    private static String buildSuffix(boolean pvp, boolean leader, String staffRole) {
        StringBuilder sb = new StringBuilder();
        sb.append("&f");
        //Checking the PvP Argument
        if (pvp) {
            sb.append(" [&4PvP&f]");
        }
        //Checking the leader argument
        if (leader) {
            sb.append(" [&6K&f]");
        }
        //Checking the staff role argument
        if (staffRole.equalsIgnoreCase("Mod")) {
            sb.append(" [&7Mod&f]");
        } else if (staffRole.equalsIgnoreCase("Admin")) {
            sb.append(" [&8Admin&f]");
        } else if (staffRole.equalsIgnoreCase("Dev")) {
            sb.append(" [&6Dev&f]");
        } else if (staffRole.equalsIgnoreCase("Owner")) {
            sb.append(" [&bOwner&f]");
        }
        return sb.toString();
    }
}
