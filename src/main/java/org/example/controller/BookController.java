package org.example.controller;

import org.example.dto.BookDto;
import org.example.mapper.AddBookMapper;
import org.example.service.BookService;
import org.example.service.CategoryService;
import org.example.service.TakeAcceptBookService;
import org.example.util.GetAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private TakeAcceptBookService takeAcceptBookService;
    @GetMapping("/getAllBook")
    public ResponseEntity<?> getBookList() {
        return new ResponseEntity<>( bookService.getAllBook(), HttpStatus.OK);
    }

    @GetMapping("/{data}")
    public ResponseEntity<?> searchBook(@PathVariable String data) {
        return ResponseEntity.ok(bookService.searchByData(data));
    }
    @PostMapping("/createBook}")
    private ResponseEntity<?> addBook(@RequestBody BookDto bookDto) {
        return ResponseEntity.ok(bookService.addBook(bookDto));
    }
    @DeleteMapping("/{id}")
    private ResponseEntity<?> removeBook(@PathVariable String id) {
        return new ResponseEntity<>( bookService.removeBook(id),HttpStatus.OK);
    }
    @GetMapping("/getAllBooksOnHand")
    private ResponseEntity<?> allBooksOnHand() {
       return ResponseEntity.ok( takeAcceptBookService.getAllTakenBook());
    }

    @GetMapping("/bookHistoryById/{id}")
    private ResponseEntity<?> bookHistoryById(@PathVariable String id) {
        return ResponseEntity.ok(takeAcceptBookService.getAllBookHistoryByBookId(id));
    }
}
