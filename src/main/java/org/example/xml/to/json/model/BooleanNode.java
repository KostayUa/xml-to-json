package org.example.xml.to.json.model;

import java.util.List;
import java.util.Objects;

public class BooleanNode extends Node {
    private final boolean value;

    public BooleanNode(boolean value) {
        this.value = value;
    }

    public BooleanNode(boolean value, List<Attribute> attributes) {
        super(attributes);
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BooleanNode that = (BooleanNode) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), value);
    }

    @Override
    public String toString() {
        return "BooleanNode{" +
            "value=" + value +
            '}';
    }
}
