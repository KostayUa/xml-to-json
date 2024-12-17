package org.example.xml.to.json.model;

import java.util.Objects;

public class Attribute {
    private final String name;
    private final String value;

    public Attribute(String name, String value) {
        Objects.requireNonNull(name, "Name must not be null");
        Objects.requireNonNull(value, "Value must not be null");
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attribute that = (Attribute) o;
        return Objects.equals(name, that.name) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }

    @Override
    public String toString() {
        return "Attribute("
            + "name='" + getName() + '\''
            + ", value='" + getValue() + '\''
            + ")";
    }
}
