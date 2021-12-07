package com.test.project.security.api.repository;

import com.test.project.security.model.User;
import com.test.project.util.GenericDao;

public interface UserRepository extends GenericDao<User> {
    User loadUserByUsername(String username);
}
