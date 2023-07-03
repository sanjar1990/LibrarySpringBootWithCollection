package org.example.exception;

public class NothingFoundException extends RuntimeException{
    public NothingFoundException(String message){
        super(message);
    }
}
