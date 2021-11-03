package com.test.project.controller;

import com.test.annotations.Autowired;
import com.test.annotations.Component;
import com.test.project.api.service.UserService;

@Component
public class UserController {
    @Autowired
    private UserService userService;

    public String getUrl() {
        return userService.getUrl();
    }
}
