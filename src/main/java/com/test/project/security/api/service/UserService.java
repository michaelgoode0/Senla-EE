package com.test.project.security.api.service;

import com.test.project.security.dto.LoginDto;
import com.test.project.security.dto.UserDto;

public interface UserService {
    UserDto signUp(LoginDto dto);
    String signIn(LoginDto dto);
    UserDto delete(Long id);
    UserDto loadByUsername(String username);
}
