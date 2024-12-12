package org.example.xml.to.json.model;

import java.util.List;
import java.util.Objects;

public class NullNode extends Node {

    public NullNode(String name) {
        super(name);
    }

    public NullNode(String name, List<Attribute> attributes) {
        super(name, attributes);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NullNode nullNode = (NullNode) o;
        return super.equalsName(nullNode) && super.equalsAttributes(nullNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAttributes());
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
        return "NullNode(name='" + getName() + '\'' + attrs + ")";
    }
}
