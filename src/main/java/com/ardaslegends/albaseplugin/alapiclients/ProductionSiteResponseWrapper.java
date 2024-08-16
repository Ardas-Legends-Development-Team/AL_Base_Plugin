package com.ardaslegends.albaseplugin.alapiclients;

import com.ardaslegends.albaseplugin.models.BackendModels.BackendProductionSiteModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * This is a Wrapper used to wrap the ProductionSiteResponseString into the ProductionSiteModel
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductionSiteResponseWrapper {
    private List<BackendProductionSiteModel> content;

    public List<BackendProductionSiteModel> getContent() {
        return content;
    }

    public void setContent(List<BackendProductionSiteModel> content) {
        this.content = content;
    }
}
