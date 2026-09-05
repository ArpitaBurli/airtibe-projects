package org.example.exception;

public class ReservationNotFoundException extends LibraryException {

    public ReservationNotFoundException(String message) {
        super(message);
    }
}