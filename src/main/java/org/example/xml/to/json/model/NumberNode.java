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
        if (!super.equals(o)) return false;
        NumberNode that = (NumberNode) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), value);
    }

    @Override
    public String toString() {
        return "NumberNode{" +
            "value=" + value +
            '}';
    }
}
