package com.ardaslegends.albaseplugin.alapiclients;

import com.ardaslegends.albaseplugin.models.BackendModels.BackendFactionModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * This is a Wrapper used to wrap the FactionResponseString into the FactionModel
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FactionResponseWrapper {
    private List<BackendFactionModel> content;

    public List<BackendFactionModel> getContent() {
        return content;
    }

    public void setContent(List<BackendFactionModel> content) {
        this.content = content;
    }
}
