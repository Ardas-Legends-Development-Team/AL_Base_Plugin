package com.ardaslegends.albaseplugin.commands;

import com.ardaslegends.albaseplugin.AL_Base_Plugin;
import com.ardaslegends.albaseplugin.alapiclients.PluginDBManager;
import com.ardaslegends.albaseplugin.models.DBModels.DBFactionModel;
import com.ardaslegends.albaseplugin.models.DBModels.DBPlayerModel;
import com.ardaslegends.albaseplugin.resources.DepositConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandDeposit implements CommandExecutor {

    private final FileConfiguration depositConfig = DepositConfig.getDepositConfig();

    private static final String msgPrefix = AL_Base_Plugin.getMsgPrefix();
    private static final String errorPrefix = AL_Base_Plugin.getErrorPrefix();

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(errorPrefix + "Only players can use this command.");
            return true;
        }
        Player player = (Player) sender;
        if (AL_Base_Plugin.getBackendOnline()) {
            return backendOnlineCommand(player, args);
        } else {
            return backendOfflineCommand(player, args);
        }
    }

    /**
     * Handles the command execution when the backend is online.
     * Currently the backend does not support depositing money, so the offline functionality is used.
     * ToDO: Implement the backendOnlineCommand once the backend supports depositing money, remove the temporary solution.
     *
     * @param player the player executing the command
     * @param args the command arguments
     * @return true if the command was processed successfully, false otherwise
     */
    private boolean backendOnlineCommand (Player player, String[] args) {
        player.sendMessage(errorPrefix + "The backend doesn't support depositing money yet. Plugin is using the offline functionality.");
        return backendOfflineCommand(player, args);
    }

    /**
     * Handles the command execution when the backend is offline.
     *
     * @param player the player executing the command
     * @param args the command arguments
     * @return true if the command was processed successfully, false otherwise
     */
    private boolean backendOfflineCommand (Player player, String[] args) {
        if (args.length == 1) {
            // The command has the subcommands "deposit" and "info" for players, that need no further arguments
            if (args[0].equalsIgnoreCase("deposit")) {
                double value;

                //Get the handheld item of the player and get it´s value
                int heldItemSlot = player.getInventory().getHeldItemSlot();
                ItemStack heldItem = player.getInventory().getItem(heldItemSlot);
                value = calculateValue(heldItem);
                if (value <= 0) {
                    player.sendMessage(buildValueResponse(value));
                    return true;
                }

                double newDeposit = deposit(player, value);
                player.sendMessage(buildDepositResponse(value, newDeposit));
            } else if (args[0].equalsIgnoreCase("info")) {
                double deposit = info(player);
                switch ((int) deposit) {
                    case -1:
                        player.sendMessage(errorPrefix + "You can only see deposit of your faction, if you joined a faction.");
                        break;
                    case -100:
                        player.sendMessage(errorPrefix + "The database is not enabled.");
                        break;
                    default:
                        player.sendMessage(msgPrefix + "The current deposit of your faction is " + deposit + ".");
                        break;
                }
            } else {
                return false;
            }
        } else if (args.length == 0) {
            //if no argument is given, the default functionality is executed.
            double value;

            //Get the handheld item of the player and get it´s value
            int heldItemSlot = player.getInventory().getHeldItemSlot();
            ItemStack heldItem = player.getInventory().getItem(heldItemSlot);
            value = calculateValue(heldItem);
            if (value <= 0) {
                player.sendMessage(buildValueResponse(value));
                return true;
            }

            double newDeposit = deposit(player, value);
            player.sendMessage(buildDepositResponse(value, newDeposit));
        } else if (args.length == 2) {
            // The command has the subcommand "info" for staff that needs a faction name as argument
            if (args[0].equalsIgnoreCase("info")) {
                double deposit = info(args[1]);
                switch ((int) deposit) {
                    case -1:
                        player.sendMessage(errorPrefix + "The faction " + args[1] + " is not listed in the database.");
                        break;
                    case -100:
                        player.sendMessage(errorPrefix + "The database is not enabled.");
                        break;
                    default:
                        player.sendMessage(msgPrefix + "The current deposit of the faction " + args[1] + " is " + deposit + ".");
                        break;
                }
            } else {
                return false;
            }
        } else if (args.length == 3) {
            // The command has the subcommand "withdraw" for staff that needs a faction name and a value as arguments.
            if (args[0].equalsIgnoreCase("withdraw")) {
                double newDeposit = withdraw(args[1], Double.parseDouble(args[2]));
                switch ((int) newDeposit) {
                    case -1:
                        player.sendMessage(errorPrefix + "The faction " + args[1] + " is not listed in the database.");
                        break;
                    case -2:
                        player.sendMessage(errorPrefix + "The faction " + args[1] + " does not have enough deposit to withdraw. \n" +
                                args[2] + " can not be removed.");
                        break;
                    case -100:
                        player.sendMessage(errorPrefix + "The database is not enabled.");
                        break;
                    default:
                        player.sendMessage(msgPrefix + "You have successfully withdrawn " + args[2] + " from the faction " + args[1] + ". \n" +
                                "The new balance is " + newDeposit + ".");
                        break;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    private double calculateValue (ItemStack item) {
        if (item == null) {
            return -1;
        } else if (depositConfig.contains(item.getTypeId() + "." + item.getDurability())) {
            StringBuilder sb = new StringBuilder();
            sb.append(item.getTypeId()).append(".").append(item.getDurability());
            int itemValue = depositConfig.getInt(sb.toString());
            return itemValue * item.getAmount();
        } else if (depositConfig.contains(String.valueOf(item.getTypeId()))) {
            StringBuilder sb = new StringBuilder();
            sb.append(item.getTypeId());
            int itemValue = depositConfig.getInt(sb.toString());
            return itemValue * item.getAmount();
        } else {
            return -100;
        }
    }

    private String buildValueResponse (double value) {
        switch ((int) value) {
            case -1:
                return errorPrefix + "You need to hold an item in your hand to run this command.";
            case -100:
                return errorPrefix + "The item you are holding is not usable for deposit.";
            default:
                return errorPrefix + "An unexpected error happened, please contact the Admins";
        }
    }

    /**
     * Deposits a specified value into the player's faction deposit.
     *
     * @param player the player who is making the deposit
     * @param value the amount to be deposited
     * @return the new deposit amount if the player is in a faction,
     *         -1 if the player is not in a faction,
     *         -100 if the database is not enabled
     */
    private double deposit (Player player, double value) {
        if (AL_Base_Plugin.isDatabaseEnabled()) {
            DBPlayerModel playerModel = PluginDBManager.getPlayerByUUID(player.getUniqueId().toString());
            if (playerModel.getFaction() != null) {
                DBFactionModel factionModel = playerModel.getFaction();
                double newDeposit = factionModel.addDeposit(value);
                PluginDBManager.update(factionModel);
                return newDeposit;
            } else {
                player.sendMessage(errorPrefix + "You can only deposit money if you are in a faction.");
                return -1;
            }
        }
        return -100;
    }

    /**
     * Builds the response message for the deposit command.
     *
     * @param value the amount that was deposited
     * @param newDeposit the new deposit amount
     * @return the response message
     */
    private String buildDepositResponse (double value, double newDeposit) {
        switch ((int) newDeposit) {
            case -1:
                return errorPrefix + "You can only deposit money if you are in a faction.";
            case -100:
                return errorPrefix + "The database is not enabled.";
            default:
                return msgPrefix + "You have successfully deposited " + value + " into your faction. \n" +
                        "The new balance is " + newDeposit + ".";
        }
    }

    /**
     * Withdraws a specified value from the faction's deposit.
     *
     * @param factionName the name of the faction from which the deposit is to be withdrawn
     * @param value the amount to be withdrawn
     * @return the new deposit amount if the withdrawal is successful,
     *         -1 if the faction does not exist,
     *         -2 if the faction does not have enough deposit to withdraw,
     *         -100 if the database is not enabled
     */
    private double withdraw (String factionName, double value) {
        if (AL_Base_Plugin.isDatabaseEnabled()) {
            DBFactionModel factionModel = PluginDBManager.getFactionByName(factionName);
            if (factionModel != null) {
                double newDeposit = factionModel.removeDeposit(value);
                if (newDeposit >= 0) {
                    PluginDBManager.update(factionModel);
                    return newDeposit;
                } else {
                    return -2;
                }
            } else {
                return -1;
            }
        }
        return -100;
    }

    /**
     * Retrieves the current deposit amount for the player's faction.
     *
     * @param player the player whose faction deposit is to be retrieved
     * @return the current deposit amount if the player is in a faction,
     *         -1 if the player is not in a faction,
     *         -100 if the database is not enabled
     */
    private double info (Player player) {
        if (AL_Base_Plugin.isDatabaseEnabled()) {
            DBPlayerModel playerModel = PluginDBManager.getPlayerByUUID(player.getUniqueId().toString());
            if (playerModel.getFaction() != null) {
                return playerModel.getFaction().getDeposit();
            } else {
                return -1;
            }
        }
        return -100;
    }

    /**
     * Retrieves the current deposit amount for a specified faction.
     *
     * @param faction the name of the faction whose deposit is to be retrieved
     * @return the current deposit amount if the faction exists,
     *         -1 if the faction does not exist,
     *         -100 if the database is not enabled
     */
    private double info (String faction) {
        if (AL_Base_Plugin.isDatabaseEnabled()) {
            DBFactionModel factionModel = PluginDBManager.getFactionByName(faction);
            if (factionModel != null) {
                return factionModel.getDeposit();
            } else {
                return -1;
            }
        }
        return -100;
    }
}
