package org.example.xml.to.json.mapper;

public class JsonToJsonMapper {
    private final JsonMapper jsonMapper = new JsonMapper();
    private final NodeToJsonMapper nodeToJsonMapper = new NodeToJsonMapper();

    public String convert(String xml) {
        return nodeToJsonMapper.convert(jsonMapper.parse(xml));
    }
}
