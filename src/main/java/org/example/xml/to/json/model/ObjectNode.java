package org.example.xml.to.json.model;

import java.util.List;
import java.util.Objects;

public class ObjectNode extends Node {
    private final List<Node> properties;

    public ObjectNode(String nodeName, List<Node> properties) {
        super(nodeName);
        this.properties = properties;
    }

    public ObjectNode(String nodeName, List<Node> properties, List<Attribute> attributes) {
        super(nodeName, attributes);
        this.properties = properties;
    }

    public List<Node> getProperties() {
        return properties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectNode that = (ObjectNode) o;
        if (!Objects.equals(properties, that.properties)) return false;
        return super.equalsAttributes(that) && super.equalsNodeName(that);
    }

    @Override
    public int hashCode() {
        return super.hashCode(properties);
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
        return "ObjectNode(name='" + getName() + '\''
            + ", properties=" + getProperties() + attrs + ")";
    }
}
