package com.test.project.security.controller;


import com.test.project.security.api.service.UserService;
import com.test.project.security.dto.LoginDto;
import com.test.project.security.dto.UserDto;
import com.test.project.security.filter.TokenProvider;
import com.test.project.security.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    public ResponseEntity<UserDto> signUp(@PathVariable Long id) {
        UserDto response = userService.delete(id);
        return ResponseEntity.ok(response);
    }
}
