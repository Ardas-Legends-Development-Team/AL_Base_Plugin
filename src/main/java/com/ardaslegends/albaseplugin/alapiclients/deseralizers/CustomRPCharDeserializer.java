package com.ardaslegends.albaseplugin.alapiclients.deseralizers;

import com.ardaslegends.albaseplugin.models.BackendModels.BackendRPCharModel;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class CustomRPCharDeserializer extends StdDeserializer {

    public CustomRPCharDeserializer () {
        this(null);
    }

    public CustomRPCharDeserializer (Class<?> vc) {
        super(vc);
    }

    @Override
    public BackendRPCharModel deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String name = node.get("name").asText();
        boolean pvp = node.get("pvp").booleanValue();

        return new BackendRPCharModel(name, pvp);
    }
}
