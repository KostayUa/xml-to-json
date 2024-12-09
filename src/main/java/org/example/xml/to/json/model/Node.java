package org.example.xml.to.json.model;

import java.util.List;
import java.util.Objects;

public abstract class Node {
    private final String name;
    private final List<Attribute> attributes;

    public Node(String name) {
        this(name, List.of());
    }

    public Node(String name, List<Attribute> attributes) {
        Objects.requireNonNull(name, "Name must not be null");
        Objects.requireNonNull(attributes, "Attributes must not be null");
        this.name = name;
        this.attributes = attributes;
    }

    public String getName() {
        return name;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public boolean equalsAttributes(Node node) {
        return Objects.equals(attributes, node.attributes);
    }

    public boolean equalsName(Node node) {
        return Objects.equals(name, node.name);
    }

    public int hashCode(Object o) {
        return Objects.hash(o, name, attributes);
    }
}
