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
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        StringNode that = (StringNode) o;
        if (!Objects.equals(value, that.value)) return false;
        return super.equals(o);
    }

    public int hashCode() {
        return super.hashCode(value);
    }

//    @Override
//    public String toString() {
//        return "StringNode{" +
//            "value='" + value + '\'' +
//            '}';
//    }

    @Override
    public String toString() {
        return "StringNode{" +
            "value='" + value + '\'' + ", " +
            "attributes=" + getAttributes() +
            '}';
    }
}
