package com.test.project.repository;

import com.test.annotations.Component;
import com.test.annotations.Value;
import com.test.project.api.repository.UserRepository;

@Component
public class UserRepositoryImpl implements UserRepository {
    @Value(value = "my.param.db")
    private String databaseUrl;

    public String printUrl(){
        return databaseUrl;
    }
}
