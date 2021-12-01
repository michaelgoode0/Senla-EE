package com.test.project.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.project.api.service.UserProfileService;
import com.test.project.dto.UserDto;
import com.test.project.dto.UserProfileDto;
import com.test.project.exceptions.GlobalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
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
