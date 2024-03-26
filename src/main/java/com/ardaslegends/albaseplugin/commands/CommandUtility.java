package com.ardaslegends.albaseplugin.commands;

import com.ardaslegends.albaseplugin.AL_Base_Plugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandUtility implements CommandExecutor {

    private final String msgPrefix = AL_Base_Plugin.getMsgPrefix();
    private final String errorPrefix = AL_Base_Plugin.getErrorPrefix();

    /**
     * onCommand is being run if the command alutil was executed
     * The Syntax for the command is simple: /alutil [getItemInfo]
     *
     * @param sender Source of the command
     * @param command Command which was executed
     * @param label Alias of the command which was used
     * @param args Passed command arguments
     * @return returns, whether the command was successful or not
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            if (args[0].equalsIgnoreCase("getItemInfo")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    int heldItemSlot = player.getInventory().getHeldItemSlot();
                    ItemStack heldItem =
                            player.getInventory().getItem(heldItemSlot);
                    if (heldItem == null) {
                        sender.sendMessage(errorPrefix + "You need to hold an Item in your hand to run this cmd.");
                        return true;
                    }
                    sender.sendMessage(msgPrefix + "Item Info is: " + heldItem.toString());
                }
            }
        } else {
            sender.sendMessage(errorPrefix + "Wrong argument count");
            return false;
        }
        return true;
    }
}
