package org.example.controller;

import org.example.container.ComponentContainer;
import org.example.service.BookService;
import org.example.service.TakeAcceptBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Scanner;

@RestController
public class StudentMenuController {
    @Autowired
    private Scanner scanner;
    @Autowired
    private Scanner scannerLine;
    @Autowired
    private BookService bookService;
    @Autowired
    private TakeAcceptBookService takeAcceptBookService;

    @GetMapping("/book/{id}")
    public ResponseEntity<?> todayBookDeadline(@PathVariable int id){
        return ResponseEntity.ok( takeAcceptBookService.getTodayReturnBookList(id));
    }
    @GetMapping("/book/getAllBook")
    public ResponseEntity<?> getBookList() {
        return new ResponseEntity<>( bookService.getAllBook(), HttpStatus.OK);
    }
    @GetMapping("/book/{data}")
    public ResponseEntity<?> searchBook(@PathVariable String data) {
        return ResponseEntity.ok(bookService.searchByData(data));
    }

    @GetMapping("/book/take/{id}")
    public ResponseEntity<?> takeBook(@PathVariable String id) {
       return new ResponseEntity<>(takeAcceptBookService.takeBook(id), HttpStatus.OK);
    }

    @PutMapping("/book/return/{id}")
    private ResponseEntity<?> returnBook(@PathVariable String id) {
        return ResponseEntity.ok(takeAcceptBookService.returnBook(id));
    }

    @GetMapping("/book/booksOnHand/{id}")
    private ResponseEntity<?> booksOnHand(@PathVariable String id) {
        return new ResponseEntity<>(takeAcceptBookService.getAllTakenBookById(id),HttpStatus.OK);
    }
    @GetMapping("/book/takenBookHistory/{id}")
    private ResponseEntity<?> TakenBookHistory(@PathVariable String id) {
        return ResponseEntity.ok(takeAcceptBookService.getAllTakenBookHistory(id));
    }
}
