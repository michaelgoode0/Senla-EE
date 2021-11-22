package com.test.project.dao;

import com.test.project.util.AbstractDao;
import com.test.project.api.repository.UserRepository;
import com.test.project.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends AbstractDao<User> implements UserRepository {


    @Override
    public User create(User entity) {
        return super.create(entity);
    }

    @Override
    public User update(User entity) {
        return super.update(entity);
    }

    @Override
    public User read(Long id) {
        return super.read(id);
    }

    @Override
    public User delete(Long entityId) {
        return super.delete(entityId);
    }
}
