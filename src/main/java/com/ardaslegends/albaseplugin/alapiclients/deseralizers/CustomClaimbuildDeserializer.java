package com.ardaslegends.albaseplugin.alapiclients.deseralizers;

import com.ardaslegends.albaseplugin.alapiclients.ProductionSiteResponseWrapper;
import com.ardaslegends.albaseplugin.models.BackendModels.BackendClaimbuildModel;
import com.ardaslegends.albaseplugin.models.BackendModels.BackendProductionSiteModel;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.List;

public class CustomClaimbuildDeserializer extends StdDeserializer {

    ObjectMapper mapper = new ObjectMapper();

    public CustomClaimbuildDeserializer () {
        this(null);
    }

    protected CustomClaimbuildDeserializer(Class vc) {
        super(vc);
    }

    /**
     * @param jsonParser
     * @param deserializationContext
     * @return
     * @throws IOException
     * @throws JacksonException
     */
    @Override
    public BackendClaimbuildModel deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        BackendClaimbuildModel cbModel;

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        String name = node.get("name").asText();
        int region = node.get("region").asInt();
        String claimBuildType = node.get("claimBuildType").asText();

        String productionSiteString = node.get("productionSites").toString();
        List<BackendProductionSiteModel> productionSites = mapper.readValue(productionSiteString, new TypeReference<List<BackendProductionSiteModel>>() {});

        cbModel = new BackendClaimbuildModel(name, region, claimBuildType);
        cbModel.setProductionSites(productionSites);

        return cbModel;
    }
}
