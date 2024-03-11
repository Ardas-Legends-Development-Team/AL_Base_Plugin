package com.ardaslegends.albaseplugin.alapiclients;

import com.ardaslegends.albaseplugin.models.BackendModels.BackendClaimbuildModel;
import com.ardaslegends.albaseplugin.models.BackendModels.BackendRegionModel;
import com.ardaslegends.albaseplugin.models.SavefileModels.SafefileClaimbuildModel;
import com.ardaslegends.albaseplugin.models.SavefileModels.SafefileFactionModel;
import com.ardaslegends.albaseplugin.models.SavefileModels.SafefileRegionModel;

import java.util.List;
import java.util.stream.Collectors;

/***
 * This Class is a Wrapper, that wraps from the BackendModels to SafefileModels
 */
public class BackendToSafefileWrapper {

    private static final ALApiClient apiClient = new ALApiClient();

    /***
     * This Method wraps the given List of claimbuilds of the given Faction to the SafefileModel version.
     * The SafefileModel is build up as following:
     * one FactionModel has a List of RegionModels
     * one RegionModel has a List of ClaimbuildModels
     * one ClaimbuildModel has a List of ResourceModels
     * @param factionName The name of the faction
     * @param claimbuilds The list of claimbuilds
     * @return The SafefileFactionModel through which everything is accessible
     */
    public static SafefileFactionModel wrapClaimbuildsOfFaction (String factionName, List<BackendClaimbuildModel> claimbuilds) {

        //Create a SafefileModel for the faction
        SafefileFactionModel faction = new SafefileFactionModel(factionName);

        //map the List of claimbuilds to an array of regionNumbers, where all regions the Faction owns are listed
        int[] regions = claimbuilds.stream().mapToInt(BackendClaimbuildModel::getRegionNr).sorted().distinct().toArray();

        //For each of those regions, create a SafefileModel and add it to the RegionList of the Faction
        for (int regionNr : regions) {
            SafefileRegionModel region = new SafefileRegionModel(regionNr);
            BackendRegionModel regionModel = apiClient.getRegionInfo(regionNr);
            region.setRegionName(regionModel.getName());
            region.setRegionType(regionModel.getRegion_type());
            faction.addRegion(region);
        }

        //Than for each SafefileRegionModel of the Faction, filter the claimbuilds of that region from the claimbuild List
        for (SafefileRegionModel region : faction.getRegions()) {
            List<BackendClaimbuildModel> claimbuildsOfRegion = claimbuilds.stream().filter(cb -> cb.getRegionNr() == region.getRegionNumber()).collect(Collectors.toList());

            //For each claimbuild, set up a SafefileModel, add the Resources to that claimbuild and add the claimbuild to the Regions claimbuildList
            for (BackendClaimbuildModel cbModel : claimbuildsOfRegion) {
                SafefileClaimbuildModel claimbuild = new SafefileClaimbuildModel(cbModel.getName());
                claimbuild.setClaimbuildType(cbModel.getClaimbuildType());

                //ToDo: Create the Resource SafefileModels for the claimbuild and add them to the ResourceList

                //Add the claimbuild to the regions ClaimbuildList
                region.addClaimbuild(claimbuild);
            }
        }
        return faction;
    }

}
