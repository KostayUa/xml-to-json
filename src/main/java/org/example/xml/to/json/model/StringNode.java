package org.example.xml.to.json.model;

import java.util.List;
import java.util.Objects;

public class StringNode extends Node {
    private final String value;

    public StringNode(String value) {
        this.value = value;
    }

    public StringNode(String value, List<Attribute> attributes) {
        super(attributes);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StringNode that = (StringNode) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), value);
    }

    @Override
    public String toString() {
        return "StringNode{" +
            "value='" + value + '\'' +
            '}';
    }
}
