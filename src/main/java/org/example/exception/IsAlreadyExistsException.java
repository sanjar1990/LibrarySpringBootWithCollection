package org.example.exception;

public class IsAlreadyExistsException extends RuntimeException{
    public IsAlreadyExistsException(String message){
        super(message);
    }
}
