package com.ardaslegends.albaseplugin;

import com.ardaslegends.albaseplugin.alapiclients.ALApiClient;
import com.ardaslegends.albaseplugin.commands.CommandRPChar;
import com.ardaslegends.albaseplugin.commands.CommandStockpile;
import com.ardaslegends.albaseplugin.models.FactionModel;
import com.ardaslegends.albaseplugin.resources.StockpileConfig;
import com.ardaslegends.albaseplugin.tabcompletion.TabCompletionStockpile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class AL_Base_Plugin extends JavaPlugin {

    private static       AL_Base_Plugin     plugin;
    private final        Logger             logger      = Bukkit.getServer().getLogger();
    private static final String             msgPrefix   = ChatColor.GOLD + "[AL-Plugin] " + ChatColor.RESET;
    private static final String             errorPrefix = ChatColor.DARK_RED + "[Error]" + ChatColor.RESET;
    private static final ALApiClient        apiClient   = new ALApiClient();
    private static final List<FactionModel> factions    = new ArrayList<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        //Setting up the config files
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        //Setting up the stockpileConfig.yml
        StockpileConfig.addDefaults();

        //Loading all Factions
        factions.addAll(setUpFactions());
        logger.log(Level.INFO, factions.toString());

        //Setting up the stockpile feature if enabled
        if (getConfig().contains("feature.stockpile")) {
            //Registering the stockpile command
            getCommand("stockpile").setExecutor(new CommandStockpile());
            getCommand("stockpile").setTabCompleter(new TabCompletionStockpile());
        }

        //Setting up the rpchar feature if enabled
        if (getConfig().contains("feature.rpchar")) {
            //Registering the rpchar command
            getCommand("rpchar").setExecutor(new CommandRPChar());
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /**
     * This is a temporary solution to autocomplete faction names in commands
     * Long term the factions will be loaded from the backend
     * @return A List of Faction Models containing all relevant factions of the server
     */
    private List<FactionModel> setUpFactions(){
        List<FactionModel> factionModelList = new ArrayList<>();
        FactionModel angmar = new FactionModel("Angmar");
        factionModelList.add(angmar);
        FactionModel bree = new FactionModel("Bree");
        factionModelList.add(bree);
        FactionModel dale = new FactionModel("Dale");
        factionModelList.add(dale);
        FactionModel dolAmroth = new FactionModel("Dol Amroth");
        factionModelList.add(dolAmroth);
        FactionModel dolGuldur = new FactionModel("Dol Guldur");
        factionModelList.add(dolGuldur);
        FactionModel dorwinion = new FactionModel("Dorwinion");
        factionModelList.add(dorwinion);
        FactionModel dunland = new FactionModel("Dunland");
        factionModelList.add(dunland);
        FactionModel durinsFolk = new FactionModel("Durin's Folk");
        factionModelList.add(durinsFolk);
        FactionModel eredLuin = new FactionModel("Ered Luin");
        factionModelList.add(eredLuin);
        FactionModel gondor = new FactionModel("Gondor");
        factionModelList.add(gondor);
        FactionModel gulfOfHarad = new FactionModel("Gulf of Harad");
        factionModelList.add(gulfOfHarad);
        FactionModel gundabad = new FactionModel("Gundabad");
        factionModelList.add(gundabad);
        FactionModel halfTrolls = new FactionModel("Half-Trolls");
        factionModelList.add(halfTrolls);
        FactionModel harnennor = new FactionModel("Harnennor");
        factionModelList.add(harnennor);
        FactionModel hobbits = new FactionModel("Hobbits");
        factionModelList.add(hobbits);
        FactionModel isengard = new FactionModel("Isengard");
        factionModelList.add(isengard);
        FactionModel lindon = new FactionModel("Lindon");
        factionModelList.add(lindon);
        FactionModel lothlorien = new FactionModel("Lothlórien");
        factionModelList.add(lothlorien);
        FactionModel mordor = new FactionModel("Mordor");
        factionModelList.add(mordor);
        FactionModel morwaith = new FactionModel("Morwaith");
        factionModelList.add(morwaith);
        FactionModel nomads = new FactionModel("Nomads");
        factionModelList.add(nomads);
        FactionModel rangers = new FactionModel("Rangers of the North");
        factionModelList.add(rangers);
        FactionModel rhudel = new FactionModel("Rhúdel");
        factionModelList.add(rhudel);
        FactionModel rivendell = new FactionModel("Rivendell");
        factionModelList.add(rivendell);
        FactionModel rohan = new FactionModel("Rohan");
        factionModelList.add(rohan);
        FactionModel southronCoast = new FactionModel("Southron Coast");
        factionModelList.add(southronCoast);
        FactionModel taurethrim = new FactionModel("Taurethrim");
        factionModelList.add(taurethrim);
        FactionModel umbar = new FactionModel("Umbar");
        factionModelList.add(umbar);
        FactionModel woodlandRealm = new FactionModel("Woodland Realm");
        factionModelList.add(woodlandRealm);
        FactionModel wanderer = new FactionModel("Wanderer");
        factionModelList.add(wanderer);
        return factionModelList;
    }

    /**
     * reloading all config files
     */
    public void reload() {
        reloadConfig();
    }

    public static AL_Base_Plugin getPlugin() {
        return plugin;
    }

    public static String getMsgPrefix() {
        return msgPrefix;
    }

    public static String getErrorPrefix(){
        return errorPrefix;
    }

    public static List<FactionModel> getFactions() {
        return factions;
    }
}
