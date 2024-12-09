package org.example.xml.to.json.model;

import java.util.List;
import java.util.Objects;

public class ArrayNode extends Node {
    private final List<Node> items;

    public ArrayNode(String nodeName, List<Node> items) {
        super(nodeName);
        this.items = items;
    }

    public ArrayNode(String nodeName, List<Node> items, List<Attribute> attributes) {
        super(nodeName, attributes);
        this.items = items;
    }

    public List<Node> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayNode arrayNode = (ArrayNode) o;
        if (!Objects.equals(items, arrayNode.items)) return false;
        return super.equalsAttributes(arrayNode) && super.equalsName(arrayNode);
    }

    @Override
    public int hashCode() {
        return super.hashCode(items);
    }

    @Override
    public String toString() {
        List<Attribute> attributes = getAttributes();
        String attrs;
        if (attributes.isEmpty()) {
            attrs = "";
        } else {
            attrs = ", attributes=" + attributes;
        }
        return "ArrayNode(name='" + getName() + '\''
            + ", items=" + getItems() + attrs + ")";
    }
}
