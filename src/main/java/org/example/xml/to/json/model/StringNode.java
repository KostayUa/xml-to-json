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
        return super.equals2(that);
    }

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
        return "StringNode(value='" + getValue() + '\'' + attrs + ")";
    }

    public String toString2() {
        List<Attribute> attributes = getAttributes();
        String attrs;
        if (attributes.isEmpty()) {
            attrs = "";
        } else {
            attrs = ", attributes=" + attributes;
        }
        StringBuilder builder = new StringBuilder();
        builder.append("StringNode(value='");
        builder.append(getValue());
        builder.append('\'');
        builder.append(attrs);
        builder.append(")");
        return builder.toString();
    }

//    public String toString2() {
//        List<Attribute> attributes = getAttributes();
//        String attrs;
//        StringBuilder builder = new StringBuilder();
//        builder.append("StringNode(value='");
//        builder.append(getValue());
//        builder.append('\'');
//        if (attributes.isEmpty()) {
//            attrs = "";
//            builder.append(attrs);
//        } else {
//            attrs = ", attributes=";
//            builder.append(attrs);
//            builder.append(attributes);
//        }
//        builder.append(")");
//        return builder.toString();
//    }
}
