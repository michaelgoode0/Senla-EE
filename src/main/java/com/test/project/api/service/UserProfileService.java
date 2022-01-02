package com.test.project.api.service;

import com.test.project.dto.UserProfileDto;
import com.test.project.dto.UserProfileWithAllDto;

public interface UserProfileService  {
    UserProfileDto update(UserProfileDto userProfileDto);
    UserProfileWithAllDto read(Long id);
    UserProfileWithAllDto delete(Long id);
}
