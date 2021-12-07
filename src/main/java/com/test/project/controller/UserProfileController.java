package com.test.project.controller;

import com.test.project.api.service.UserProfileService;
import com.test.project.dto.UserProfileDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/profiles")
public class UserProfileController {

    private final UserProfileService userProfileService;
    @PostMapping
    public ResponseEntity<UserProfileDto> create(@RequestBody UserProfileDto request){
        UserProfileDto response = userProfileService.create(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDto> get(@PathVariable Long id){
        UserProfileDto response = userProfileService.read(id);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<UserProfileDto> delete(@PathVariable Long id){
        UserProfileDto response = userProfileService.delete(id);
        return ResponseEntity.ok(response);
    }
    @PutMapping
    public ResponseEntity<UserProfileDto> update(@RequestBody UserProfileDto request){
        UserProfileDto response = userProfileService.update(request);
        return ResponseEntity.ok(response);
    }
}
