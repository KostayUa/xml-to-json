package org.example.xml.to.json.mapper;

import org.example.xml.to.json.model.*;

import java.util.List;

public class NodeToXmlMapper {

    public String convert(Node node, NullTagFormat format) {
        StringBuilder builder = new StringBuilder();
        printNode(node, builder, format);
        return builder.toString();
    }

    private void printNode(Node node, StringBuilder builder, NullTagFormat format) {
        //start tag (add attributes)
        printOpenTag(node, builder, format);
        //add simple value
        printValueTag(node, builder, format);
        //close tag
        printCloseTag(node, builder, format);
    }

    private void printOpenTag(Node node, StringBuilder builder, NullTagFormat format) {
        if (node instanceof NullNode) {
            if (format == NullTagFormat.FULL) {
                printOpenTagFull(node, builder);
            } else {
                printOpenTagShort(node, builder);
            }
        } else {
            printOpenTagFull(node, builder);
        }
    }

    private void printOpenTagFull(Node node, StringBuilder builder) {
        builder.append("<");
        builder.append(node.getName());
        printAttributes(node, builder);
        builder.append(">");
    }

    private void printOpenTagShort(Node node, StringBuilder builder) {
        builder.append("<");
        builder.append(node.getName());
        printAttributes(node, builder);
        builder.append("/>");
    }

    private void printAttributes(Node node, StringBuilder builder) {
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

    private void printValueTag(Node node, StringBuilder builder, NullTagFormat format) {
        if (node instanceof NullNode) {

        } else if (node instanceof StringNode stringNode) {
            builder.append(stringNode.getValue());
        } else if (node instanceof NumberNode numberNode) {
            builder.append(numberNode.getValue().toPlainString());
        } else if (node instanceof BooleanNode booleanNode) {
            builder.append(booleanNode.getValue());
        } else if (node instanceof ObjectNode objectNode) {
            printListNode(objectNode.getProperties(), builder, format);
        } else if (node instanceof ArrayNode arrayNode) {
            printListNode(arrayNode.getItems(), builder, format);
        } else {
            throw new IllegalArgumentException("Unknown the Node type: " + node);
        }
    }

    private void printListNode(List<Node> nodes, StringBuilder builder, NullTagFormat format) {
        for (Node node : nodes) {
            printNode(node, builder, format);
        }
    }

    private void printCloseTag(Node node, StringBuilder builder, NullTagFormat format) {
        if (node instanceof NullNode) {
            //FULL -> </name>
            //SHORT -> ""
            if (format == NullTagFormat.FULL) {
                builder.append("</").append(node.getName()).append(">");
            }
        } else {
            //</name>
            builder.append("</").append(node.getName()).append(">");
        }
    }

    public enum NullTagFormat {
        FULL,
        SHORT
    }
}
