package com.test.project.security.controller;


import com.test.project.security.api.service.UserService;
import com.test.project.security.dto.LoginDto;
import com.test.project.security.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController{

    private final UserService userService;

    @PostMapping("/signin")
    public String getToken(@RequestBody LoginDto request) {
        return userService.signIn(request);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody LoginDto request) {
        UserDto response = userService.signUp(request);
        return ResponseEntity.ok(response);
    }
    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> delete(@PathVariable Long id) {
        UserDto response = userService.delete(id);
        return ResponseEntity.ok(response);
    }
}
