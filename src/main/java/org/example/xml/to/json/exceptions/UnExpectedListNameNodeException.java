package org.example.xml.to.json.exceptions;

import org.w3c.dom.Node;

public class UnExpectedListNameNodeException extends RuntimeException {

    public UnExpectedListNameNodeException(Node node) {
        super("Unexpected node type." + node.getNodeName());
    }
}
