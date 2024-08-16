package com.ardaslegends.albaseplugin.commands;

import com.ardaslegends.albaseplugin.AL_Base_Plugin;
import com.ardaslegends.albaseplugin.alapiclients.ALApiClient;
import com.ardaslegends.albaseplugin.alapiclients.BackendToSafefileWrapper;
import com.ardaslegends.albaseplugin.models.BackendModels.BackendClaimbuildModel;
import com.ardaslegends.albaseplugin.models.SavefileModels.SafefileFactionModel;
import com.ardaslegends.albaseplugin.resources.SafeFileManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CommandRefreshResources implements CommandExecutor {

    private final ALApiClient apiClient = new ALApiClient();
    private final Logger logger = AL_Base_Plugin.getALLogger();
    private final String msgPrefix = AL_Base_Plugin.getMsgPrefix();
    private final String errorPrefix = AL_Base_Plugin.getErrorPrefix();

    /**
     * onCommand is being run if the command refreshResources was executed
     * The Syntax for the command is simple: /refreshResources [faction]
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
            if (args.length == 1) {
                String factionname = args[0];

                List<BackendClaimbuildModel> claimbuilds = apiClient.getClaimbuilds(factionname);
                logger.log(Level.INFO, msgPrefix + "Fetched " + claimbuilds.size() + " Claimbuilds from the backend for " + factionname);

                List<BackendClaimbuildModel> filteredClaimbuilds = claimbuilds.stream().filter(claimbuild -> {
                    boolean keepOrCastle = claimbuild.getClaimbuildType().equals("Keep") || claimbuild.getClaimbuildType().equals("Castle");
                    return !keepOrCastle;
                }).collect(Collectors.toList());
                logger.log(Level.INFO, msgPrefix + filteredClaimbuilds.size() + " Claimbuilds with Production Sites for " + factionname);
                SafefileFactionModel faction = BackendToSafefileWrapper.wrapClaimbuildsOfFaction(factionname, filteredClaimbuilds);
                SafeFileManager.overwriteFactionResources(faction);
            } else {
                sender.sendMessage(errorPrefix + "Wrong argument count.");
                return false;
            }
        } else {
            sender.sendMessage(errorPrefix + "The backend is offline, this command needs the backend to be online. Please contact the Devs.");
        }
        return true;
    }
}
