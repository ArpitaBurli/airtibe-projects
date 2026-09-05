package org.example.exception;

public class BranchNotFoundException extends LibraryException {

    public BranchNotFoundException(String message) {
        super(message);
    }
}