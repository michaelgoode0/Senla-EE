package com.test.project.security.controller;


import com.test.project.security.api.repository.UserRepository;
import com.test.project.security.api.service.UserService;
import com.test.project.security.dto.LoginDto;
import com.test.project.security.dto.UserWithAllDto;
import com.test.project.util.AuthNameHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController{

    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/signin")
    public String getToken(@RequestBody LoginDto request) {
        return userService.signIn(request);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserWithAllDto> signUp(@RequestBody LoginDto request) {
        UserWithAllDto response = userService.signUp(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserWithAllDto> getUser(@PathVariable String username){
        UserWithAllDto response = userService.loadByUsername(username);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user")
    public  ResponseEntity<String> getUsername(){
        return  ResponseEntity.ok(AuthNameHolder.getAuthUsername());
    }
}
