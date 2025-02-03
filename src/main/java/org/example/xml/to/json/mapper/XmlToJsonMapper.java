package org.example.xml.to.json.mapper;

import org.example.xml.to.json.model.Node;

public class XmlToJsonMapper {
    private final XmlMapper xmlMapper = new XmlMapper();
    private final NodeToJsonMapper nodeToJsonMapper = new NodeToJsonMapper();

    public String convert(String xml) {
        Node node = xmlMapper.parse(xml);
        return nodeToJsonMapper.convert(node);
    }
}
