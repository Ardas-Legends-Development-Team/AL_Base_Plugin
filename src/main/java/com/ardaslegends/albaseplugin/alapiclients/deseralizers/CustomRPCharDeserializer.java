package com.ardaslegends.albaseplugin.alapiclients.deseralizers;

import com.ardaslegends.albaseplugin.models.RPCharModel;
import com.fasterxml.jackson.core.JacksonException;
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
    public RPCharModel deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String name = node.get("name").asText();
        boolean pvp = node.get("pvp").booleanValue();

        return new RPCharModel(name, pvp);
    }
}
