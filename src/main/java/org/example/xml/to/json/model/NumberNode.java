package org.example.xml.to.json.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class NumberNode extends Node {
    private final BigDecimal value;

    public NumberNode(String name, BigDecimal value) {
        super(name);
        this.value = value;
    }

    public NumberNode(String name, BigDecimal value, List<Attribute> attributes) {
        super(name, attributes);
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberNode that = (NumberNode) o;
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
        return "NumberNode(name='" + getName() + '\''
            + ", value='" + getValue() + '\'' + attrs + ")";
    }
}
