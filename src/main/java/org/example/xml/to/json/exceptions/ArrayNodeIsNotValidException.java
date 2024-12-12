package org.example.xml.to.json.exceptions;

import org.example.xml.to.json.model.Node;

public class ArrayNodeIsNotValidException extends RuntimeException {

    public ArrayNodeIsNotValidException(Node node) {
        super("ArrayNode has an invalid element." + node.getName());
    }
}
