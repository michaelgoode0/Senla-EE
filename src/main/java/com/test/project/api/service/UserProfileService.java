package com.test.project.api.service;

import com.test.project.dto.UserDto;
import com.test.project.dto.UserProfileDto;
import com.test.project.entity.User;

public interface UserProfileService {
    UserProfileDto create(UserProfileDto user);
    UserProfileDto update(UserProfileDto user);
    UserProfileDto read(Long id);
    UserProfileDto delete(Long id);

}
