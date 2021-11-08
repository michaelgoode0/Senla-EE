package com.test.project.api.repository;

import com.test.project.entity.User;

import java.util.List;

public interface UserRepository {
    User create(User user);
    User update(User user);
    User read(Long id);
    User delete(Long id);
    User getById(Long id);


}
