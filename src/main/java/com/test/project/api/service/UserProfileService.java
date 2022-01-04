package com.test.project.api.service;

import com.test.project.dto.UserProfileDto;
import com.test.project.dto.UserProfileWithAllDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserProfileService  {
    UserProfileDto update(UserProfileDto userProfileDto);
    UserProfileWithAllDto read(Long id);
    void delete(Long id);
    Page<UserProfileDto> findAll(Pageable pageable);
}
