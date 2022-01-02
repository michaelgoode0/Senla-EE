package com.test.project.security.api.service;

import com.test.project.security.dto.LoginDto;
import com.test.project.security.dto.UserDto;
import com.test.project.security.dto.UserWithAllDto;

public interface UserService {
    UserWithAllDto signUp(LoginDto dto);
    String signIn(LoginDto dto);
    UserWithAllDto delete(Long id);
    UserWithAllDto loadByUsername(String username);
}
