package org.example.exception;

public class EnterValidPhoneNumberException extends RuntimeException{
    public EnterValidPhoneNumberException(String message){
        super(message);
    }
}
