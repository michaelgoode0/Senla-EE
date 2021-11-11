package com.test.project.api.service;

import com.test.project.dto.UserDto;

public interface UserService {
    UserDto create(UserDto userDto);
    UserDto update(UserDto userDto);
    UserDto read(Long id);
    UserDto delete(Long id);
}
