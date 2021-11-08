package com.test.project.api.repository;

import com.test.project.entity.User;
import com.test.project.entity.UserProfile;

public interface UserProfileRepository {
    UserProfile create(UserProfile userProfile);
    UserProfile update(UserProfile userProfile);
    UserProfile read(Long id);
    UserProfile delete(Long id);
}
