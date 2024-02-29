package com.ardaslegends.albaseplugin.alapiclients;

import com.ardaslegends.albaseplugin.models.ClaimbuildModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * This is a Wrapper wrapping the ClaimbuildResponseString into the Claimbuild Model
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClaimbuildResponseWrapper {
    private List<ClaimbuildModel> content;

    public List<ClaimbuildModel> getContent() {
        return content;
    }

    public void setContent(List<ClaimbuildModel> content) {
        this.content = content;
    }
}
