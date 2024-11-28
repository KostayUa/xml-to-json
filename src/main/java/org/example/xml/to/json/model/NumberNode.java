package org.example.xml.to.json.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class NumberNode extends Node {
    private final BigDecimal value;

    public NumberNode(BigDecimal value) {
        this.value = value;
    }

    public NumberNode(BigDecimal value, List<Attribute> attributes) {
        super(attributes);
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
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode(value);
    }

    @Override
    public String toString() {
        return "NumberNode{" +
            "value='" + getValue() + '\'' + ", " +
            "attributes=" + getAttributes() +
            '}';
    }
}
