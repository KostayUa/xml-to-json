package org.example.xml.to.json.mapper;

import org.example.xml.to.json.model.*;

import java.util.List;

public class NodeToXmlMapper {

    public String convert(Node node, NullTagFormat nullTagFormat) {
        StringBuilder builder = new StringBuilder();
        buildXml(node, builder, nullTagFormat);
        return builder.toString();
    }

    private void buildXml(Node node, StringBuilder builder, NullTagFormat nullTagFormat) {
        builder.append("<").append(node.getName());
        appendAttribute(node, builder);

        if (node instanceof NullNode) {
            if (nullTagFormat == NullTagFormat.FULL) {
                builder.append(">");
                builder.append("</").append(node.getName()).append(">");
            } else {
                builder.append("/>");
            }
        } else if (node instanceof StringNode stringNode) {
            builder.append(">");
            builder.append(stringNode.getValue());
            builder.append("</").append(node.getName()).append(">");
        } else if (node instanceof NumberNode numberNode) {
            builder.append(">");
            builder.append(numberNode.getValue());
            builder.append("</").append(node.getName()).append(">");
        } else if (node instanceof BooleanNode booleanNode) {
            builder.append(">");
            builder.append(booleanNode.getValue());
            builder.append("</").append(node.getName()).append(">");
        } else if (node instanceof ObjectNode objectNode) {
            builder.append(">");
            List<Node> properties = objectNode.getProperties();
            for (Node property : properties) {
                buildXml(property, builder, nullTagFormat);
            }
            builder.append("</").append(node.getName()).append(">");
        } else if (node instanceof ArrayNode arrayNode) {
            builder.append(">");
            List<Node> items = arrayNode.getItems();
            for (Node item : items) {
                buildXml(item, builder, nullTagFormat);
            }
            builder.append("</").append(node.getName()).append(">");
        }
    }

    private void appendAttribute(Node node, StringBuilder builder) {
        List<Attribute> attributes = node.getAttributes();
        if (attributes == null || attributes.isEmpty()) {
            return;
        }
        for (Attribute attribute : attributes) {
            builder.append(" ");
            builder.append(attribute.getName());
            builder.append("=\"");
            builder.append(attribute.getValue());
            builder.append("\"");
        }
    }

    public enum NullTagFormat {
        FULL,
        SHORT
    }
}
