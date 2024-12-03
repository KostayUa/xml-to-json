package org.example.xml.to.json.model;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ObjectNode extends Node {
    private final Map<String, Node> properties;

    public ObjectNode(Map<String, Node> properties) {
        this.properties = properties;
    }

    public ObjectNode(Map<String, Node> properties, List<Attribute> attributes) {
        super(attributes);
        this.properties = properties;
    }

    public Map<String, Node> getProperties() {
        return properties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectNode that = (ObjectNode) o;
        if (!Objects.equals(properties, that.properties)) return false;
        return super.equals2(that);
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
        return "ObjectNode(properties=" + getProperties() + attrs + ")";
    }
}
