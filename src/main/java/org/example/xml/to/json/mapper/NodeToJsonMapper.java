package org.example.xml.to.json.mapper;

import org.example.xml.to.json.model.*;

import java.util.List;

public class NodeToJsonMapper {

    public String convert(Node node) {
        StringBuilder builder = new StringBuilder();
        printValue(node, builder);
        return builder.toString();
    }

    private void printValue(Node node, StringBuilder builder) {
        if (node instanceof NullNode nullNode) {
            printNullNode(nullNode, builder);
        } else if (node instanceof StringNode stringNode) {
            printStringNode(stringNode, builder);
        } else if (node instanceof NumberNode numberNode) {
            printNumberNode(numberNode, builder);
        } else if (node instanceof BooleanNode booleanNode) {
            printBooleanNode(booleanNode, builder);
        } else if (node instanceof ObjectNode objectNode) {
            printObjectNode(objectNode, builder);
        } else if (node instanceof ArrayNode arrayNode) {
            printArrayNode(arrayNode, builder);
        } else {
            throw new IllegalArgumentException("Unknown Node type: " + node);
        }
    }

    private void printNullNode(NullNode nullNode, StringBuilder builder) {
        if (nullNode.getName() == null) {
            builder.append("null");
        } else {
            builder.append("\"").append(nullNode.getName()).append("\":null");
        }
    }

    private void printStringNode(StringNode stringNode, StringBuilder builder) {
        if (stringNode.getName() == null) {
            builder.append("\"").append(stringNode.getValue()).append("\"");
        } else {
            builder.append("\"")
                .append(stringNode.getName())
                .append("\":\"")
                .append(stringNode.getValue())
                .append("\"");
        }
    }

    private void printNumberNode(NumberNode numberNode, StringBuilder builder) {
        if (numberNode.getName() == null) {
            builder.append(numberNode.getValue().toPlainString());
        } else {
            builder.append("\"")
                .append(numberNode.getName())
                .append("\":")
                .append(numberNode.getValue().toPlainString());
        }
    }

    private void printBooleanNode(BooleanNode booleanNode, StringBuilder builder) {
        if (booleanNode.getName() == null) {
            builder.append(booleanNode.getValue());
        } else {
            builder.append("\"")
                .append(booleanNode.getName())
                .append("\":")
                .append(booleanNode.getValue());
        }
    }

    private void printObjectNode(ObjectNode objectNode, StringBuilder builder) {
        if (objectNode.getProperties().isEmpty()) {
            if (objectNode.getName() == null) {
                builder.append("{}");
            } else {
                builder.append("\"").append(objectNode.getName()).append("\":{}");
            }
        } else {
            if (objectNode.getName() == null) {
                builder.append("{");
            } else {
                builder.append("\"").append(objectNode.getName()).append("\":{");
            }
            printListNode(objectNode.getProperties(), builder);
            builder.append("}");
        }
    }

    private void printArrayNode(ArrayNode arrayNode, StringBuilder builder) {
        if (arrayNode.getItems().isEmpty()) {
            if (arrayNode.getName() == null) {
                builder.append("[]");
            } else {
                builder.append("\"").append(arrayNode.getName()).append("\":[]");
            }
        } else {
            if (arrayNode.getName() == null) {
                builder.append("[");
            } else {
                builder.append("\"").append(arrayNode.getName()).append("\":[");
            }
            printListNode(arrayNode.getItems(), builder);
            builder.append("]");
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
