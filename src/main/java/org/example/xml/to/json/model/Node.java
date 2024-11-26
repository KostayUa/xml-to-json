package org.example.xml.to.json.model;

import java.util.List;
import java.util.Objects;

public abstract class Node {
    private final List<Attribute> attributes;

//    public Node() {
//    }

    public Node() {
        this.attributes = null;
    }

    public Node(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(attributes, node.attributes);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(attributes);
    }

    @Override
    public String toString() {
        return "Node{" +
            "attributes=" + attributes +
            '}';
    }
}
