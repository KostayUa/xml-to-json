package org.example.xml.to.json.exceptions;

public class UnexpectedException extends RuntimeException {
    public UnexpectedException(Throwable cause) {
        super("Unexpected Exception", cause);
    }
}
