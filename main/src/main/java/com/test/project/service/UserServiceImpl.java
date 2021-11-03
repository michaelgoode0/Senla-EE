package com.test.project.service;

import com.test.annotations.Autowired;
import com.test.annotations.Component;
import com.test.project.api.repository.UserRepository;
import com.test.project.api.service.UserService;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public String getUrl(){
        return userRepository.getUrl();
    }
}
