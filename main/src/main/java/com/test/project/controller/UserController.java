package com.test.project.controller;

import com.test.annotations.Autowired;
import com.test.annotations.Component;
import com.test.project.api.controller.UserControllerInt;
import com.test.project.api.service.UserService;

@Component
public class UserController implements UserControllerInt {
    @Autowired
    private UserService userService;


    @Override
    public String display() {
        return userService.printUrl();
    }
}
