package org.example.xml.to.json.exceptions;

public class ParseXmlException extends RuntimeException {
    public ParseXmlException(Throwable cause) {
        super("Error of the parsing XML", cause);
    }
}
