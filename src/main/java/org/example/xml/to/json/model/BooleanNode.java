package org.example.xml.to.json.model;

import java.util.List;
import java.util.Objects;

public class BooleanNode extends Node {
    private final boolean value;

    public BooleanNode(String name, boolean value) {
        super(name);
        this.value = value;
    }

    public BooleanNode(String name, boolean value, List<Attribute> attributes) {
        super(name, attributes);
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BooleanNode that = (BooleanNode) o;
        if (!Objects.equals(value, that.value)) return false;
        return super.equalsAttributes(that) && super.equalsName(that);
    }

    @Override
    public int hashCode() {
        return super.hashCode(value);
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
        return "BooleanNode(name='" + getName() + '\''
            + ", value='" + getValue() + '\'' + attrs + ")";
    }
}
