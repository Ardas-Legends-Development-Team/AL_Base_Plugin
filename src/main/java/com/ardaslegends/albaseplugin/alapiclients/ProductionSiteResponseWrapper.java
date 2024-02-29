package com.ardaslegends.albaseplugin.alapiclients;

import com.ardaslegends.albaseplugin.models.ProductionSiteModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * This is a Wrapper wrapping the ProductionSiteResponseString into the ProductionSiteModel
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductionSiteResponseWrapper {
    private List<ProductionSiteModel> content;

    public List<ProductionSiteModel> getContent() {
        return content;
    }

    public void setContent(List<ProductionSiteModel> content) {
        this.content = content;
    }
}
