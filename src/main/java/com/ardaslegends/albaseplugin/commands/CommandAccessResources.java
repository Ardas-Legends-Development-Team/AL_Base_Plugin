package com.ardaslegends.albaseplugin.commands;

import com.ardaslegends.albaseplugin.AL_Base_Plugin;
import com.ardaslegends.albaseplugin.alapiclients.ALApiClient;
import com.ardaslegends.albaseplugin.models.BackendModels.BackendFactionModel;
import com.ardaslegends.albaseplugin.models.BackendModels.BackendPlayerModel;
import com.ardaslegends.albaseplugin.models.SavefileModels.SafefileFactionModel;
import com.ardaslegends.albaseplugin.models.SavefileModels.SafefileRegionModel;
import com.ardaslegends.albaseplugin.resources.ResourceConfig;
import com.ardaslegends.albaseplugin.resources.SafeFileManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandAccessResources implements CommandExecutor {

    private final ALApiClient apiClient = new ALApiClient();
    private final Logger logger = AL_Base_Plugin.getALLogger();
    private final String msgPrefix = AL_Base_Plugin.getMsgPrefix();
    private final String errorPrefix = AL_Base_Plugin.getErrorPrefix();
    private final FileConfiguration resourceConfig = ResourceConfig.getResourceConfig();

    //This hashMap keeps track of whether a factions resources is being accessed atm or not.
    //True = locked, false/not contained = unlocked/accessible
    private static HashMap<String, Boolean> factionResourceLock = new HashMap<>();

    /**
     * onCommand is being run if the command accessResources was executed
     * The Syntax for the command is simple: /accessResources
     *
     * @param sender Source of the command
     * @param command Command which was executed
     * @param label Alias of the command which was used
     * @param args Passed command arguments
     * @return returns, whether the command was successful or not
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (AL_Base_Plugin.getBackendOnline()) {
            if (sender instanceof Player) {
                //Fetch the Player from the backend and load the factionResources
                Player executingPlayer = (Player) sender;
                BackendPlayerModel playerModel = apiClient.getPlayerByIGN(executingPlayer.getName());
                //We´ll have to see if this works.
                //In theory only one faction in the backend should have the name.
                //Thus we can filter for the factionName of the players faction and findAny in the stream (Should only be one in the stream)
                BackendFactionModel factionModel = apiClient.getFactions().stream().filter(backendFactionModel -> backendFactionModel.getName().equals(playerModel.getFaction())).findAny().get();
                if (factionModel == null) {
                    sender.sendMessage(errorPrefix + "Something went wrong loading the faction. Please try again later or contact the devs for support.");
                    return true;
                }

                if (factionModel.getLeader() != null) {
                    if (executingPlayer.getName().equals(factionModel.getLeader())) {
                        if (checkResourceLock(playerModel)) {
                            executingPlayer.sendMessage(errorPrefix + "Your factions resources are currently accessed by someone else. Please try again later.");
                            logger.log(Level.INFO, executingPlayer.getName() + " tried to access the resources of "
                                    + playerModel.getFaction() + " but they are currently locked.");
                        }
                        //Accessing the Inventory, he is the faction Leader
                        Inventory factionResource = createInventory(executingPlayer, playerModel.getFaction());
                        executingPlayer.openInventory(factionResource);
                    } else {
                        sender.sendMessage(msgPrefix + "Sorry, only your faction leader can access the Monthly Resources.");
                        return true;
                    }
                } else {
                    if (checkResourceLock(playerModel)) {
                        executingPlayer.sendMessage(errorPrefix + "Your factions resources are currently accessed by someone else. Please try again later.");
                        logger.log(Level.INFO, executingPlayer.getName() + " tried to access the resources of "
                                + playerModel.getFaction() + " but they are currently locked.");
                    }
                    //Accessing the inventory because there is no faction Leader
                    Inventory factionResource = createInventory(executingPlayer, playerModel.getFaction());
                    executingPlayer.openInventory(factionResource);
                }
            } else {
                sender.sendMessage(errorPrefix + "This command can only be run by a player on the server, not on console.");
            }
        } else {
            sender.sendMessage(errorPrefix + "The backend is offline, this command needs the backend to be online. Please contact the Devs.");
        }
        return true;
    }

    /**
     * This Method checks if the Faction Recoures of the given player can be accessed or are accessed by a different player
     * at the time and thus are locked.
     *
     * @param playerModel the BackendPlayerModel of the player who wants to access his factions Resources
     * @return true if the faction resources are accessed by a different player and thus locked, false if they aren´t locked
     */
    private boolean checkResourceLock (BackendPlayerModel playerModel) {
        if (factionResourceLock.containsKey(playerModel.getFaction())) {
            if (factionResourceLock.get(playerModel.getFaction())) {
                return false;
            } else {
                factionResourceLock.put(playerModel.getFaction(), true);
                logger.log(Level.INFO, msgPrefix + "Resources of "
                        + playerModel.getFaction() + " have been locked by "
                        + playerModel.getIgn());
                return true;
            }
        } else {
            factionResourceLock.put(playerModel.getFaction(), true);
            logger.log(Level.INFO, msgPrefix + "Resources of "
                    + playerModel.getFaction() + " have been locked by "
                    + playerModel.getIgn());
            return true;
        }
    }

    private Inventory createInventory (Player player, String factionName) {
        SafefileFactionModel factionResources = SafeFileManager.loadFactionResources(factionName);
        int regionCount = factionResources.getRegions().size();
        Inventory factionInventory = Bukkit.createInventory(player, defineInventorySize(regionCount), msgPrefix + factionName + "-Resources");
        for (SafefileRegionModel regionModel : factionResources.getRegions()) {
            String configPath = "region" + regionModel.getRegionType().toLowerCase();
            int itemId = resourceConfig.getInt(configPath + ".id");
            short dataValue = resourceConfig.contains(configPath + ".dataInfo") ?  (short) resourceConfig.getInt(configPath + ".dataInfo") : 0;
            ItemStack inventoryButton = new ItemStack(itemId, 1, dataValue);
            ItemMeta inventoryButtonItemMeta = inventoryButton.getItemMeta();
            inventoryButtonItemMeta.setDisplayName(regionModel.getRegionNumber() + ": " + regionModel.getRegionName());
            inventoryButton.setItemMeta(inventoryButtonItemMeta);
            factionInventory.addItem(inventoryButton);
        }
        return factionInventory;
    }

    /**
     * This Method is used to calculate the Inventory Size (Multiple of 9)
     *
     * @param objectCount the count of the Objects meant to be displayed
     * @return The Size of the inventory
     */
    private int defineInventorySize (int objectCount) {
        if (objectCount % 9 != 0) {
            return (objectCount/9) + 1;
        } else {
            return objectCount/9;
        }
    }
}
