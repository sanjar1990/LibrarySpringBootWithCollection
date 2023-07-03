package org.example.exception;

public class BookIsNotExistsException extends RuntimeException {
    public BookIsNotExistsException(String message){
        super(message);
    }
}
