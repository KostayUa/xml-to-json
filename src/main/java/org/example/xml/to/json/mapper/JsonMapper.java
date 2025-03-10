package org.example.xml.to.json.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import org.example.xml.to.json.exceptions.ParseJsonException;
import org.example.xml.to.json.model.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonMapper {

    public Node parse(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(json);
            return parseJsonNode(null, root);
        } catch (JsonProcessingException e) {
            throw new ParseJsonException(e);
        }
    }

    private Node parseJsonNode(String name, JsonNode jsonNode) {
        JsonNodeType nodeType = jsonNode.getNodeType();
        return switch (nodeType) {
            case NULL -> name == null ? new NullNode() : new NullNode(name);
            case BOOLEAN -> name == null
                ? new BooleanNode(jsonNode.asBoolean())
                : new BooleanNode(name, jsonNode.asBoolean());
            case STRING -> name == null
                ? new StringNode(jsonNode.textValue())
                : new StringNode(name, jsonNode.textValue());
            case NUMBER -> name == null
                ? new NumberNode(new BigDecimal(jsonNode.asText()))
                : new NumberNode(name, new BigDecimal(jsonNode.asText()));
            case OBJECT -> parseObjectNode(name, jsonNode);
            case ARRAY -> parseArrayNode(name, jsonNode);
            default -> throw new IllegalArgumentException("Unknown JsonNode type: " + jsonNode);
        };
    }

    private ObjectNode parseObjectNode(String name, JsonNode jsonNode) {
        List<Node> properties = new ArrayList<>();
        Iterator<String> fieldNames = jsonNode.fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            Node child = parseJsonNode(fieldName, jsonNode.get(fieldName));
            properties.add(child);
        }
        return name != null ? new ObjectNode(name, properties) : new ObjectNode(properties);
    }

    private Node parseArrayNode(String name, JsonNode jsonNode) {
        List<Node> items = new ArrayList<>();
        for (JsonNode item : jsonNode) {
            items.add(parseJsonNode(null, item));
        }
        return name != null ? new ArrayNode(name, items) : new ArrayNode(items);
    }
}
