package org.example.xml.to.json.mapper;

import org.example.xml.to.json.model.*;

import java.util.List;

public class NodeToJsonMapper {

    public String convert(Node node) {
        StringBuilder builder = new StringBuilder();
        boolean needEnvelop = needEnvelop(node);
        if (needEnvelop) {
            builder.append("{");
        }
        printValue(node, builder);
        if (needEnvelop) {
            builder.append("}");
        }
        return builder.toString();
    }

    private boolean needEnvelop(Node node) {
        return node.hasName();
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
        if (nullNode.hasName()) {
            printName(nullNode.getName(), builder);
        }
        builder.append("null");
    }

    private void printStringNode(StringNode stringNode, StringBuilder builder) {
        if (stringNode.hasName()) {
            printName(stringNode.getName(), builder);
        }
        builder.append("\"")
            .append(stringNode.getValue())
            .append("\"");
    }

    private void printNumberNode(NumberNode numberNode, StringBuilder builder) {
        if (numberNode.hasName()) {
            printName(numberNode.getName(), builder);
        }
        builder.append(numberNode.getValue());
    }

    private void printBooleanNode(BooleanNode booleanNode, StringBuilder builder) {
        if (booleanNode.hasName()) {
            printName(booleanNode.getName(), builder);
        }
        builder.append(booleanNode.getValue());
    }

    private void printName(String name, StringBuilder builder) {
        builder.append("\"")
            .append(name)
            .append("\": ");
    }

    private void printObjectNode(ObjectNode objectNode, StringBuilder builder) {
        if (objectNode.hasName()) {
            printName(objectNode.getName(), builder);
        }
        builder.append("{");
        printListNode(objectNode.getProperties(), builder);
        builder.append("}");
    }

    private void printArrayNode(ArrayNode arrayNode, StringBuilder builder) {
        if (arrayNode.hasName()) {
            printName(arrayNode.getName(), builder);
        }
        builder.append("[");
        printListNode(arrayNode.getItems(), builder);
        builder.append("]");
    }

    private void printListNode(List<Node> nodes, StringBuilder builder) {
        int size = nodes.size();
        for (int i = 0; i < size; i++) {
            printValue(nodes.get(i), builder);
            if (needAddDelimiter(i, size)) {
                builder.append(", ");
            }
        }
    }

    private boolean needAddDelimiter(int index, int size) {
        return index < size - 1;
    }
}
