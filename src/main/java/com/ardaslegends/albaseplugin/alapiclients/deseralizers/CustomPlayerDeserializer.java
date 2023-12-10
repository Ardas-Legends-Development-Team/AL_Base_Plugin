package com.ardaslegends.albaseplugin.alapiclients.deseralizers;

import com.ardaslegends.albaseplugin.AL_Base_Plugin;
import com.ardaslegends.albaseplugin.models.PlayerModel;
import com.ardaslegends.albaseplugin.models.RPCharModel;
import com.fasterxml.jackson.core.JacksonException;
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
    public PlayerModel deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String ign = node.get("ign").asText();
        String faction = node.get("faction").asText();
        boolean isStaff = node.get("isStaff").booleanValue();
        String rpCharString = node.get("rpChar").toString();

        RPCharModel rpCharModel = mapper.readValue(rpCharString, RPCharModel.class);

        return new PlayerModel(ign, faction, rpCharModel, isStaff);
    }
}
