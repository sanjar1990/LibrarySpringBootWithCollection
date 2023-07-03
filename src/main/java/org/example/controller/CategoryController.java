package org.example.controller;

import org.example.dto.CategoryDto;
import org.example.service.CategoryService;
import org.example.util.GetAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Scanner;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getAll")
    private ResponseEntity<?> categoryList() {
       return ResponseEntity.ok(categoryService.getAllCategory());
    }
    @PostMapping("/createCategory")
    private ResponseEntity<?> addCategory(@RequestBody CategoryDto categoryDto) {
        return  new ResponseEntity<>(categoryService.addCategory(categoryDto), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    private ResponseEntity<?> removeCategory(@PathVariable String id) {
        return ResponseEntity.ok(categoryService.removeCategory(id));
    }
}
