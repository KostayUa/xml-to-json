package org.example.xml.to.json.model;

import java.util.List;
import java.util.Objects;

public class ArrayNode extends Node {
    private final List<Node> items;

    public ArrayNode(List<Node> items) {
        this.items = items;
    }

    public ArrayNode(List<Node> items, List<Attribute> attributes) {
        super(attributes);
        this.items = items;
    }

    public List<Node> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ArrayNode arrayNode = (ArrayNode) o;
        return Objects.equals(items, arrayNode.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), items);
    }

    //    @Override
//    public int hashCode() {
//        int result = 17;
//        result = 31 * result + (super.hashCode());
//        result = 31 * result + (items != null ? items.hashCode() : 0);
//        return result;
//    }
}
