package com.test.project.security.api.service;

import com.test.project.security.dto.LoginDto;
import com.test.project.security.dto.UserWithAllDto;
import com.test.project.security.enums.RoleName;

public interface UserService {
    UserWithAllDto signUp(LoginDto dto, RoleName roleName);
    String signIn(LoginDto dto);
    void delete(Long id);
    UserWithAllDto loadByUsername(String username);
}
