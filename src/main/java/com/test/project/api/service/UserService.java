package com.test.project.api.service;

import com.test.project.dto.UserDto;
import com.test.project.entity.User;

import java.util.List;

public interface UserService {
    UserDto create(UserDto userDto);
    UserDto update(UserDto userDto);
    UserDto read(Long id);
    UserDto delete(Long id);
    List<User> getList();
    User getById(Long id);
}
