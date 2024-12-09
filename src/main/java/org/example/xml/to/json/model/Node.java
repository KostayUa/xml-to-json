package org.example.xml.to.json.model;

import java.util.List;
import java.util.Objects;

public abstract class Node {
    private final String nodeName;
    private final List<Attribute> attributes;

    public Node(String name) {
        this(name, List.of());
    }

    public Node(String name, List<Attribute> attributes) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(attributes);
        this.nodeName = name;
        this.attributes = attributes;
    }

    public String getNodeName() {
        return nodeName;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public boolean equalsAttributes(Node node) {
        return Objects.equals(attributes, node.attributes);
    }

    public boolean equalsNodeName(Node node) {
        return Objects.equals(nodeName, node.nodeName);
    }

    public int hashCode(Object o) {
        return Objects.hash(o, nodeName, attributes);
    }
}
