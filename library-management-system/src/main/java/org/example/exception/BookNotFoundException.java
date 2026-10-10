package org.example.exception;

public class  BookNotFoundException extends LibraryException {

   public BookNotFoundException(String message){
        super(message);
    }
}
