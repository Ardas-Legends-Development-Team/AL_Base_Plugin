package com.ardaslegends.albaseplugin.alapiclients;

import com.ardaslegends.albaseplugin.models.BackendModels.BackendClaimbuildModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * This is a Wrapper used to wrap the ClaimbuildResponseString into the ClaimbuildModel
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClaimbuildResponseWrapper {
    private List<BackendClaimbuildModel> content;

    public List<BackendClaimbuildModel> getContent() {
        return content;
    }

    public void setContent(List<BackendClaimbuildModel> content) {
        this.content = content;
    }
}
