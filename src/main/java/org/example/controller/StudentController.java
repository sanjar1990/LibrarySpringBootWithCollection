package org.example.controller;
import org.example.dto.ProfileDto;
import org.example.service.ProfileService;
import org.example.util.GetAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Scanner;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private ProfileService profileService;
    @GetMapping("/getAll")
    private ResponseEntity<?> getAllStudentList() {
       return ResponseEntity.ok(profileService.getAllStudent());
    }
    @GetMapping("/{data}")
    private ResponseEntity<?> searchStudent(@PathVariable String data) {
        return ResponseEntity.ok(profileService.searchStudent(data));

    }
    @PutMapping("changeStudentStatus/{id}")
    private ResponseEntity<?> blockStudent(@RequestBody ProfileDto profileDto,
                                           @PathVariable String id) {
       return ResponseEntity.ok(profileService.blockStudent(id,profileDto));

    }

    @GetMapping("getStudentTakenBook/{id}")
    private ResponseEntity<?> findStudentTakenBook(@PathVariable String id) {
        return ResponseEntity.ok( profileService.getStudentBookInfo(id));

    }
}
