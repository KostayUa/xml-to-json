package org.example.xml.to.json.model;

import java.util.List;
import java.util.Objects;

public class StringNode extends Node {
    private final String value;

    public StringNode(String name, String value) {
        super(name);
        this.value = value;
    }

    public StringNode(String name, String value, List<Attribute> attributes) {
        super(name, attributes);
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
        if (!super.equalsNode(that)) return false;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + value.hashCode();
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
        return "StringNode(name='" + getName()
            + "', value='" + getValue() + '\'' + attrs + ")";
    }

    public String toString2() {
        StringBuilder builder = new StringBuilder("StringNode(");
        appendName(builder);
        appendValue(builder);
        appendAttributes(builder);
        builder.append(")");
        return builder.toString();
    }

    private void appendName(StringBuilder builder) {
        builder.append("name='");
        builder.append(getName());
        builder.append("'");
    }

    private void appendValue(StringBuilder builder) {
        builder.append(", value='");
        builder.append(getValue());
        builder.append("'");
    }

    private void appendAttributes(StringBuilder builder) {
        List<Attribute> attributes = getAttributes();
        if (!attributes.isEmpty()) {
            builder.append(", attributes=");
            builder.append(attributes);
        }
    }
}
