package com.test.project.controller;

import com.test.project.api.service.UserProfileService;
import com.test.project.dto.UserProfileDto;
import com.test.project.dto.UserProfileWithAllDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/profiles")
@Validated
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileWithAllDto> get(@PathVariable @NotNull(message = "Profiles id can not be null")
                                                         @Positive(message = "Profiles id can not be negative") Long id){
        UserProfileWithAllDto response = userProfileService.read(id);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<UserProfileWithAllDto> delete(@PathVariable @NotNull(message = "Profiles id can not be null")
                                                            @Positive(message = "Profiles id can not be negative") Long id){
        UserProfileWithAllDto response = userProfileService.delete(id);
        return ResponseEntity.ok(response);
    }
    @PutMapping
    public ResponseEntity<UserProfileDto> update(@RequestBody UserProfileDto request){
        UserProfileDto response = userProfileService.update(request);
        return ResponseEntity.ok(response);
    }
}
