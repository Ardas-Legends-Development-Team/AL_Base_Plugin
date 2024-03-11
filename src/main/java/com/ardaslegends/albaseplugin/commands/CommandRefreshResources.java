package com.ardaslegends.albaseplugin.commands;

import com.ardaslegends.albaseplugin.AL_Base_Plugin;
import com.ardaslegends.albaseplugin.alapiclients.ALApiClient;
import com.ardaslegends.albaseplugin.models.BackendModels.BackendClaimbuildModel;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.stream.Collectors;

public class CommandRefreshResources implements CommandExecutor {

    private final ALApiClient apiClient = new ALApiClient();
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
        if (args.length == 1) {
            String factionname = args[0];

            List<BackendClaimbuildModel> claimbuilds = apiClient.getClaimbuilds(factionname);
            sender.sendMessage(msgPrefix + "Fetched " + claimbuilds.size() + " Claimbuilds from the backend for " + factionname);

            List<BackendClaimbuildModel> filteredClaimbuilds = claimbuilds.stream().filter(claimbuild -> {
                boolean keepOrCastle = claimbuild.getClaimbuildType().equals("Keep") || claimbuild.getClaimbuildType().equals("Castle");
                return !keepOrCastle;
            }).collect(Collectors.toList());
            sender.sendMessage(msgPrefix + filteredClaimbuilds.size() + " Claimbuilds with Production Sites");
            //ToDo: use the BackendSafefileWrapper to wrap the BackendModels to SafefileModels and save them.
        } else {
            sender.sendMessage(errorPrefix + "Wrong argument count.");
            return false;
        }
        return true;
    }
}
