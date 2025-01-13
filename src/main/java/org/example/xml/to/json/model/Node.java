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
        Objects.requireNonNull(attributes, "Attributes must not be null");
        this.name = name;
        this.attributes = attributes;
    }

    public String getName() {
        return name;
    }

    public boolean hasName() {
        return getName() != null;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public boolean equalsNode(Node node) {
        return Objects.equals(name, node.name) && Objects.equals(attributes, node.attributes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, attributes);
    }
}
