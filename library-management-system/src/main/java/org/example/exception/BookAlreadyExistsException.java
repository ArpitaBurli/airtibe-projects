package org.example.exception;

public class BookAlreadyExistsException extends LibraryException{
    public BookAlreadyExistsException(String message) {
        super(message);
    }
}
