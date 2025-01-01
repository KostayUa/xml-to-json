package org.example.xml.to.json.mapper;

import org.example.xml.to.json.model.*;

import java.util.List;

public class NodeToJsonMapper {

    public String convert(Node node) {
        StringBuilder builder = new StringBuilder();
        printJsonFromNode(node, builder);
        return builder.toString();
    }

    private void printJsonFromNode(Node node, StringBuilder builder) {
        builder.append("{");
        printValue(node, builder);
        builder.append("}");
    }

    private void printValue(Node node, StringBuilder builder) {
        if (node instanceof NullNode) {
            builder.append("\"").append(node.getName()).append("\":null");
        } else if (node instanceof StringNode stringNode) {
            builder.append("\"")
                .append(stringNode.getName())
                .append("\":\"")
                .append(stringNode.getValue())
                .append("\"");
        } else if (node instanceof NumberNode numberNode) {
            builder.append("\"")
                .append(numberNode.getName())
                .append("\":")
                .append(numberNode.getValue().toPlainString());
        } else if (node instanceof BooleanNode booleanNode) {
            builder.append("\"")
                .append(booleanNode.getName())
                .append("\":")
                .append(booleanNode.getValue());
        } else if (node instanceof ObjectNode objectNode) {
            printObjectNode(objectNode, builder);
        } else if (node instanceof ArrayNode arrayNode) {
            printArrayNode(arrayNode, builder);
        } else {
            throw new IllegalArgumentException("Unknown Node type: " + node);
        }
    }

    private void printObjectNode(ObjectNode objectNode, StringBuilder builder) {
        if (objectNode.getProperties().isEmpty()) {
            builder.append("\"").append(objectNode.getName()).append("\":{}");
        } else {
            builder.append("\"").append(objectNode.getName()).append("\":{");
            List<Node> properties = objectNode.getProperties();
            printListNode(properties, builder);
            builder.append("}");
        }
    }

    private void printArrayNode(ArrayNode arrayNode, StringBuilder builder) {
        if (arrayNode.getItems().isEmpty()) {
            builder.append("\"").append(arrayNode.getName()).append("\":[]");
        } else {
            builder.append("\"").append(arrayNode.getName()).append("\":[{");
            List<Node> items = arrayNode.getItems();
            printListNode(items, builder);
            builder.append("}]");
        }
    }

    private void printListNode(List<Node> nodes, StringBuilder builder) {
        for (int i = 0; i < nodes.size(); i++) {
            printValue(nodes.get(i), builder);
            if (i < nodes.size() - 1) {
                builder.append(",");
            }
        }
    }
}
