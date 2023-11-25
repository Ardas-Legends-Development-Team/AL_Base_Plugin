package com.ardaslegends.albaseplugin.alapiclients;

import com.ardaslegends.albaseplugin.models.FactionModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * This is a Wrapper used to wrap the FactionResponseString into the FactionModel
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FactionResponseWrapper {
    private List<FactionModel> content;

    public List<FactionModel> getContent() {
        return content;
    }

    public void setContent(List<FactionModel> content) {
        this.content = content;
    }
}
