package org.example.xml.to.json.exceptions;

public class ArrayNodeIsNotValidException extends RuntimeException {

    public ArrayNodeIsNotValidException() {
        super();
    }

    public ArrayNodeIsNotValidException(String message) {
        super(message);
    }

    public ArrayNodeIsNotValidException(String message, Throwable cause) {
        super(message, cause);
    }
}
