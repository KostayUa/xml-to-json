package org.example.xml.to.json.model;

import java.util.List;
import java.util.Objects;

public class ArrayNode extends Node {
    private final List<Node> items;

    public ArrayNode(List<Node> items) {
        super();
        this.items = Objects.requireNonNull(items, "Items must not be null");
    }

    public ArrayNode(String name, List<Node> items) {
        super(name);
        this.items = Objects.requireNonNull(items, "Items must not be null");
    }

    public ArrayNode(String name, List<Node> items, List<Attribute> attributes) {
        super(name, attributes);
        this.items = Objects.requireNonNull(items, "Items must not be null");
    }

    public List<Node> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayNode that = (ArrayNode) o;
        if (!super.equalsNode(that)) return false;
        return Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + items.hashCode();
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
