package com.ardaslegends.albaseplugin.alapiclients;

import com.ardaslegends.albaseplugin.AL_Base_Plugin;
import com.ardaslegends.albaseplugin.models.BackendModels.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The ALApiClient is the primary connection to the backend.
 * From here all API Calls are made and handled
 */
public class ALApiClient {
    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    private final FileConfiguration config = AL_Base_Plugin.getPlugin().getConfig();
    private final Logger logger = Bukkit.getServer().getLogger();
    private final ObjectMapper mapper = new ObjectMapper();
    private final String msgPrefix = AL_Base_Plugin.getMsgPrefix();
    private final boolean prod = config.getBoolean("backend.prod");
    private final String scheme = prod ? "https" : "http";
    private final String host = prod ? config.getString("backend.host") : "localhost";
    private final int port = config.getInt("backend.port");

    /**
     * Sends a request to the backend, to get a player by his ign.
     *
     * @param ign The Players IGN.
     * @return The PlayerModel for the requested player.
     */
    public BackendPlayerModel getPlayerByIGN(String ign){
        BackendPlayerModel playerModel = new BackendPlayerModel();

        String requestUri = scheme + "://" + host + ":" + port + "/api/player/ign/" + ign;

        logger.log(Level.INFO, "Sending request: " + requestUri);

        HttpGet request = new HttpGet(requestUri);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
        }

        if (response != null) {
            if(response.getStatusLine().getStatusCode() == 200){
                HttpEntity entity = response.getEntity();
                try {
                    String entityString = EntityUtils.toString(entity);
                    playerModel = mapper.readValue(entityString,
                            BackendPlayerModel.class);
                } catch (IOException e) {
                    logger.log(Level.WARNING, e.getMessage());
                }
            }else {
                logger.log(Level.WARNING,
                        "Unexpected error: "
                                + response.getStatusLine().getStatusCode()
                                + " "
                                + response.getStatusLine().getReasonPhrase());
            }
        } else {
            logger.log(Level.WARNING, "No response received from the backend");
        }

        return playerModel;
    }

    /**
     * Sends a request to the backend, to get a Factions stockpile, by the faction name.
     *
     * @param faction the faction, of which the stockpile info is requested.
     * @return the FactionStockpileModel returned from the backend.
     */
    public BackendFactionStockpileModel getFactionStockpile(String faction){
        BackendFactionStockpileModel stockpileModel = new BackendFactionStockpileModel();

        String requestUri = scheme + "://" + host + ":" + port + "/api/faction/get/stockpile/info/" + faction;

        logger.log(Level.INFO, "Sending request: " + requestUri);

        HttpGet request = new HttpGet(requestUri);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
        }

        if (response != null) {
            if(response.getStatusLine().getStatusCode() == 200){
                HttpEntity entity = response.getEntity();
                try {
                    stockpileModel = mapper.readValue(EntityUtils.toString(entity),
                            BackendFactionStockpileModel.class);
                } catch (IOException e) {
                    logger.log(Level.WARNING, e.getMessage());
                }
            }else {
                logger.log(Level.WARNING,
                        "Unexpected error: "
                                + response.getStatusLine().getStatusCode()
                                + " "
                                + response.getStatusLine().getReasonPhrase());
            }
        } else {
            logger.log(Level.WARNING, "No response received from the backend");
            AL_Base_Plugin.setBackendOnline(false);
        }

        return stockpileModel;
    }

    /**
     * Sends a request to the backend, to get a List of all Factions.
     *
     * @return The List returned from the backend.
     */
    public List<BackendFactionModel> getFactions() {
        List<BackendFactionModel> factions = new ArrayList<>();

        String amount = "?size=100";

        String requestUri = scheme + "://" + host + ":" + port + "/api/faction" + amount;

        logger.log(Level.INFO, "Sending request: " + requestUri);

        HttpGet request = new HttpGet(requestUri);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);
            } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
        }

        if (response != null) {
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                try {
                    String entityString = EntityUtils.toString(entity);
                    factions = mapper.readValue(entityString, FactionResponseWrapper.class).getContent();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                logger.log(Level.WARNING, "Unexpected error: "
                           + response.getStatusLine().getStatusCode()
                           + " "
                           + response.getStatusLine().getReasonPhrase());
            }

            if (!AL_Base_Plugin.getBackendOnline()) {
                AL_Base_Plugin.setBackendOnline(true);
            }
        } else {
            logger.log(Level.WARNING, "No response received from the backend");
            AL_Base_Plugin.setBackendOnline(false);
        }

        return factions;
    }

    /**
     * Sends a post to the backend in order to add value to the backend.
     *
     * @param fsm the FactionStockpileModel with the faction and amount to be added for the faction.
     * @return the returned status code.
     */
    public int addStockpile(BackendFactionStockpileModel fsm) {
        try {
            String postUri = scheme + "://" + host + ":" + port + "/api/faction/update/stockpile/add";

            logger.log(Level.INFO, "Sending patch: " + postUri);

            HttpPatch request = new HttpPatch(postUri);
            StringEntity json = new StringEntity(mapper.writeValueAsString(fsm), ContentType.APPLICATION_JSON);
            request.setEntity(json);

            CloseableHttpResponse response = httpClient.execute(request);

            logger.log(Level.INFO, response.getStatusLine().toString());

            return response.getStatusLine().getStatusCode();
        } catch (IOException e) {
            logger.log(Level.WARNING, msgPrefix + e.getMessage());
            return 0;
        }
    }

    /**
     * Sends a request to the backend to get a List of all Claimbuilds of the given Faction.
     *
     * @param faction The Faction of which the claimbuilds are requested.
     * @return The List returned from the backend.
     */
    public List<BackendClaimbuildModel> getClaimbuilds (String faction) {
        List<BackendClaimbuildModel> claimbuilds = new ArrayList<>();

        String requestUri = scheme + "://" + host + ":" + port + "/api/claimbuild/faction?faction=" + faction;

        logger.log(Level.INFO, "Sending request: " + requestUri);

        HttpGet request = new HttpGet(requestUri);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
        }

        if (response != null) {
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                try {
                    String entityString = EntityUtils.toString(entity);
                    claimbuilds = mapper.readValue(entityString, new TypeReference<List<BackendClaimbuildModel>>() {});
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                logger.log(Level.WARNING, "Unexpected error: "
                        + response.getStatusLine().getStatusCode()
                        + " "
                        + response.getStatusLine().getReasonPhrase());
            }

            if (!AL_Base_Plugin.getBackendOnline()) {
                AL_Base_Plugin.setBackendOnline(true);
            }
        } else {
            logger.log(Level.WARNING, "No response received from the backend");
            AL_Base_Plugin.setBackendOnline(false);
        }

        return claimbuilds;
    }

    /**
     * Sends a request to the backend to get the information of the given region.
     *
     * @param regionNr the region ID, of the region requested.
     * @return The Region returned from the backend.
     */
    public BackendRegionModel getRegionInfo (int regionNr) {
        BackendRegionModel regionModel = new BackendRegionModel();

        String requestUri = scheme + "://" + host + ":" + port + "/api/region/" + regionNr;

        logger.log(Level.INFO, "Sending request: " + requestUri);

        HttpGet request = new HttpGet(requestUri);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
        }

        if (response != null) {
            if(response.getStatusLine().getStatusCode() == 200){
                HttpEntity entity = response.getEntity();
                try {
                    regionModel = mapper.readValue(EntityUtils.toString(entity),
                            BackendRegionModel.class);
                } catch (IOException e) {
                    logger.log(Level.WARNING, e.getMessage());
                }
            }else {
                logger.log(Level.WARNING,
                        "Unexpected error: "
                                + response.getStatusLine().getStatusCode()
                                + " "
                                + response.getStatusLine().getReasonPhrase());
            }
            if (!AL_Base_Plugin.getBackendOnline()) {
                AL_Base_Plugin.setBackendOnline(true);
            }
        } else {
            logger.log(Level.WARNING, "No response received from the backend");
            AL_Base_Plugin.setBackendOnline(false);
        }

        return regionModel;
    }
}
