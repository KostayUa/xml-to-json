package org.example.xml.to.json.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class NumberNode extends Node {
    private final BigDecimal value;

    public NumberNode(BigDecimal value) {
        super();
        this.value = Objects.requireNonNull(value, "Value must not be null");
    }

    public NumberNode(String name, BigDecimal value) {
        super(name);
        this.value = Objects.requireNonNull(value, "Value must not be null");
    }

    public NumberNode(String name, BigDecimal value, List<Attribute> attributes) {
        super(name, attributes);
        this.value = Objects.requireNonNull(value, "Value must not be null");
    }

    public String getValue() {
        return value.toPlainString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberNode that = (NumberNode) o;
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
        return "NumberNode(name='" + getName() + '\''
            + ", value='" + getValue() + '\'' + attrs + ")";
    }
}
