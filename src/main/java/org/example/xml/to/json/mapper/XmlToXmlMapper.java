package org.example.xml.to.json.mapper;

import org.example.xml.to.json.model.Node;

public class XmlToXmlMapper {
    private final XmlMapper xmlMapper = new XmlMapper();
    private final NodeToXmlMapper nodeToXmlMapper = new NodeToXmlMapper();

    public String convert(String xml) {
        Node node = xmlMapper.parse(xml);
        return nodeToXmlMapper.convert(node, NodeToXmlMapper.NullTagFormat.FULL);
    }
}
