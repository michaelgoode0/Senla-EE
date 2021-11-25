package com.test.project.dao;

import com.test.project.util.AbstractDao;
import com.test.project.api.repository.UserRepository;
import com.test.project.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends AbstractDao<User> implements UserRepository {

}
