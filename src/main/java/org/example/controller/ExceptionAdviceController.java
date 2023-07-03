package org.example.controller;
import org.example.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdviceController {
    @ExceptionHandler(NothingFoundException.class)
public ResponseEntity<String>handler(NothingFoundException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    @ExceptionHandler({ProfileNotFoundException.class, PasswordIncorrectException.class,
            ItemNotFoundException.class, IsAlreadyExistsException.class})
    public ResponseEntity<String>handler(RuntimeException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    @ExceptionHandler(EnterValidPhoneNumberException.class)
    public ResponseEntity<String> handler(EnterValidPhoneNumberException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(BookIsNotExistsException.class)
    public ResponseEntity<String> handler(BookIsNotExistsException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }


}
