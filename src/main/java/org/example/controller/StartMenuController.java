package org.example.controller;

import org.example.dto.ProfileDto;
import org.example.service.BookService;
import org.example.service.CategoryService;
import org.example.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class StartMenuController {
    @Autowired
    private BookService bookService;
    @Autowired
    private ProfileService profileService;

    @GetMapping("/book/getAllBook")
    public ResponseEntity<?> getBookList() {
   return new ResponseEntity<>( bookService.getAllBook(), HttpStatus.OK);
    }

    @GetMapping("/book/{data}")
    public ResponseEntity<?> searchBook(@PathVariable String data) {
        return ResponseEntity.ok(bookService.searchByData(data));
    }
    @GetMapping("/category/{category}")
    public ResponseEntity<?> searchByCategory(@PathVariable String category) {
       return new ResponseEntity<>(bookService.searchBookByCategory(category),HttpStatus.OK);
    }

    @GetMapping("profile/login")
    public ResponseEntity<?> login(@RequestParam("login") String login,
                                   @RequestParam("password") String password){
        return ResponseEntity.ok(profileService.login(login,password));
    }
    @PostMapping("profile/create")
    public ResponseEntity<?> registration(@RequestBody ProfileDto profileDto) {
       return ResponseEntity.ok( profileService.registration(profileDto));
    }
}
