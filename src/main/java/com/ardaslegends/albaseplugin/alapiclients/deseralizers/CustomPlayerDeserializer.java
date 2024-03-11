package com.ardaslegends.albaseplugin.alapiclients.deseralizers;

import com.ardaslegends.albaseplugin.models.BackendModels.BackendPlayerModel;
import com.ardaslegends.albaseplugin.models.BackendModels.BackendRPCharModel;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class CustomPlayerDeserializer extends StdDeserializer {

    ObjectMapper mapper = new ObjectMapper();

    public CustomPlayerDeserializer () {
        this(null);
    }

    public CustomPlayerDeserializer (Class<?> vc) {
        super(vc);
    }

    @Override
    public BackendPlayerModel deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String ign = node.get("ign").asText();
        String faction = node.get("faction").asText();
        boolean isStaff = node.get("isStaff").booleanValue();
        String rpCharString = node.get("rpChar").toString();

        BackendRPCharModel rpCharModel = mapper.readValue(rpCharString, BackendRPCharModel.class);

        return new BackendPlayerModel(ign, faction, rpCharModel, isStaff);
    }
}
