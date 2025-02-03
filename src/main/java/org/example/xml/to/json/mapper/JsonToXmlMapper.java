package org.example.xml.to.json.mapper;

import org.example.xml.to.json.model.Node;

public class JsonToXmlMapper {
    private final JsonMapper jsonMapper = new JsonMapper();
    private final NodeToXmlMapper nodeToXmlMapper = new NodeToXmlMapper();

    public String convert(String xml) {
        Node node = jsonMapper.parse(xml);
        return nodeToXmlMapper.convert(node, NodeToXmlMapper.NullTagFormat.FULL);
    }
}
