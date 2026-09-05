package org.example.exception;

public class PatronAlreadyExistsException extends LibraryException {

    public PatronAlreadyExistsException(String message) {
        super(message);
    }
}