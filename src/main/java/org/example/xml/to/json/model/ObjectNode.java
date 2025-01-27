package org.example.xml.to.json.model;

import java.util.List;
import java.util.Objects;

public class ObjectNode extends Node {
    private final List<Node> properties;

    public ObjectNode(List<Node> properties) {
        super();
        this.properties = Objects.requireNonNull(properties, "Properties must not be null");
    }

    public ObjectNode(String name, List<Node> properties) {
        super(name);
        this.properties = Objects.requireNonNull(properties, "Properties must not be null");
    }

    public ObjectNode(String name, List<Node> properties, List<Attribute> attributes) {
        super(name, attributes);
        this.properties = Objects.requireNonNull(properties, "Properties must not be null");
    }

    public List<Node> getProperties() {
        return properties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectNode that = (ObjectNode) o;
        if (!super.equalsNode(that)) return false;
        return Objects.equals(properties, that.properties);
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + properties.hashCode();
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
