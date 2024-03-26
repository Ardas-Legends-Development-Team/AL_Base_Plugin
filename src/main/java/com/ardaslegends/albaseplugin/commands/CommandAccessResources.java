package com.ardaslegends.albaseplugin.commands;

import com.ardaslegends.albaseplugin.AL_Base_Plugin;
import com.ardaslegends.albaseplugin.alapiclients.ALApiClient;
import com.ardaslegends.albaseplugin.models.BackendModels.BackendFactionModel;
import com.ardaslegends.albaseplugin.models.BackendModels.BackendPlayerModel;
import com.ardaslegends.albaseplugin.models.SavefileModels.SafefileFactionModel;
import com.ardaslegends.albaseplugin.resources.SafeFileManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandAccessResources implements CommandExecutor {

    private final ALApiClient apiClient = new ALApiClient();
    private final Logger logger = AL_Base_Plugin.getALLogger();
    private final String msgPrefix = AL_Base_Plugin.getMsgPrefix();
    private final String errorPrefix = AL_Base_Plugin.getErrorPrefix();

    //This hashMap keeps track of whether a factions resources is being accessed atm or not
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
                SafefileFactionModel factionResources = SafeFileManager.loadFactionResources(playerModel.getFaction());

                /* -> Checking if the resources are locked or accessable. Outsource to a different Method
                if (factionResourceLock.containsKey(playerModel.getFaction())) {
                    if (factionResourceLock.get(playerModel.getFaction()).booleanValue()) {
                        executingPlayer.sendMessage(errorPrefix + "Your factions resources are currently accessed by someone else. Please try again later.");
                        logger.log(Level.INFO, executingPlayer.getName() + " tried to access the resources of "
                                + playerModel.getFaction() + " but they are currently locked.");
                        return true;
                    }
                } else {
                    factionResourceLock.put(playerModel.getFaction(), true);
                    logger.log(Level.INFO, msgPrefix + "Resources of "
                            + playerModel.getFaction() + " have been locked by "
                            + executingPlayer.getName());
                }*/

                if (factionResources.getLeaderIGN() != null) {
                    //We´ll have to see if this works.
                    //In theory only one faction in the backend should have the name.
                    //Thus we can filter for the factionName of the players faction and findAny in the stream (Should only be one in the stream)
                    BackendFactionModel factionModel = apiClient.getFactions().stream().filter(backendFactionModel -> backendFactionModel.getName().equals(playerModel.getFaction())).findAny().get();
                    if (factionModel == null) {
                        sender.sendMessage(errorPrefix + "Something went wrong loading the faction. Please try again later or contact the devs for support.");
                        return true;
                    }
                    if (executingPlayer.getName().equals(factionModel.getLeader())) {
                        //ToDo: Have the player access the Inventory, he is the faction Leader
                    } else {
                        sender.sendMessage(msgPrefix + "Sorry, only your faction Leader can access the Monthly Resources.");
                        return true;
                    }
                } else {
                    //ToDo: Have the player access the Inventory, as no Leader is present
                }
            } else {
                sender.sendMessage(errorPrefix + "This command can only be run by a player on the server, not on console.");
            }
        } else {
            sender.sendMessage(errorPrefix + "The backend is offline, this command needs the backend to be online. Please contact the Devs.");
        }
        return true;
    }
}
