package com.test.project.security.api.service;

import com.test.project.security.dto.LoginDto;
import com.test.project.security.dto.UserDto;
import com.test.project.security.model.User;
import com.test.project.util.GenericDao;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    UserDto signUp(LoginDto dto);
    String signIn(LoginDto dto);
    UserDto delete(Long id);
}
