package org.example.xml.to.json.model;

public final class NullNode extends Node {
    private static final NullNode instance = new NullNode();

    private NullNode() {
        super("null");
    }

    public static NullNode getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return "NullNode{}";
    }
}
