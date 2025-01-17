package org.example.xml.to.json.exceptions;

public class ParseJsonException extends RuntimeException {
    public ParseJsonException(Throwable cause) {
        super("Error of the parsing JSON", cause);
    }
}
