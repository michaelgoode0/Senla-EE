package com.test.project.security.dao;

import com.test.project.entity.Post;
import com.test.project.security.api.repository.UserRepository;
import com.test.project.security.model.User;
import com.test.project.util.AbstractDao;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends AbstractDao<User> implements UserRepository {
    @Override
    public User loadUserByUsername(String username) {
        return  entityManager.createQuery("select us from User us where us.username=:username",User.class)
                .setParameter("username",username)
                .getSingleResult();
    }
}
