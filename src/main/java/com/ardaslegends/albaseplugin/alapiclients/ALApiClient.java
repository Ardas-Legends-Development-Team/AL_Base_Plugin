package com.ardaslegends.albaseplugin.alapiclients;

import com.ardaslegends.albaseplugin.AL_Base_Plugin;
import com.ardaslegends.albaseplugin.models.FactionStockpileModel;
import com.ardaslegends.albaseplugin.models.PlayerModel;
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
import java.util.logging.Level;
import java.util.logging.Logger;

public class ALApiClient {
    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    private final FileConfiguration config = AL_Base_Plugin.getPlugin().getConfig();
    private final Logger logger = Bukkit.getServer().getLogger();
    private final ObjectMapper mapper = new ObjectMapper();
    private final String msgPrefix = AL_Base_Plugin.getMsgPrefix();
    boolean prod = config.getBoolean("backend.prod");
    String scheme;
    String host = config.getString("backend.host");
    int port = config.getInt("backend.port");

    /**
     * Sends a request to the backend, to get a player by his ign
     *
     * @param ign The Players IGN
     * @return The PlayerModel for the requested player
     */
    public PlayerModel getPlayerByIGN(String ign){
        PlayerModel playerModel = new PlayerModel();
        scheme = prod ? "https" : "http";

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
                    playerModel = mapper.readValue(EntityUtils.toString(entity),
                                                   PlayerModel.class);
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
     * @param faction the faction, of which the stockpile info is requested
     * @return the FactionStockpileModel returned from the backend
     */
    public FactionStockpileModel getFactionStockpile(String faction){
        FactionStockpileModel stockpileModel = new FactionStockpileModel();
        scheme = prod ? "https" : "http";

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
                                                      FactionStockpileModel.class);
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

        return stockpileModel;
    }

    /**
     * Sends a post to the backend in order to add value to the backend
     * @param fsm the FactionStockpileModel with the faction and amount to be added for the faction
     * @return the returned status code
     */
    public int addStockpile(FactionStockpileModel fsm) {
        try {
            scheme = prod ? "https" : "http";
            String postUri = scheme + "://" + host + ":" + port + "/api/faction/update/stockpile/add";

            logger.log(Level.INFO, "Sending request: " + postUri);

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
}