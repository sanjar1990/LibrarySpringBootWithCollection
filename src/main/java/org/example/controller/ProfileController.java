package org.example.controller;

import org.example.dto.ProfileDto;
import org.example.entity.ProfileEntity;
import org.example.enums.ProfileStatus;
import org.example.service.ProfileService;
import org.example.util.GetAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Scanner;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;
    @GetMapping("/getAllProfile")
    private ResponseEntity<?> profileList() {
    return new ResponseEntity<>(profileService.getAllProfile(), HttpStatus.OK);
    }
    @GetMapping("/{data}")
    private ResponseEntity<?> searchProfile(@PathVariable String data) {
       return ResponseEntity.ok(profileService.searchProfile(data));
    }
    @PutMapping("/{id}")
    private ResponseEntity<?> changeProfileStatus(@RequestBody ProfileDto profileDto,
                                                  @PathVariable String id) {
        return ResponseEntity.ok( profileService.changeStatus(id,profileDto));
    }

    @PostMapping("/createProfile")
    private ResponseEntity<?> addStaff(@RequestBody ProfileDto profileDto) {
    return ResponseEntity.ok( profileService.staffRegistration(profileDto));
    }
}
