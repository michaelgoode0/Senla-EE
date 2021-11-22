package com.test.project.dao;

import com.test.project.util.AbstractDao;
import com.test.project.api.repository.UserProfileRepository;
import com.test.project.entity.UserProfile;
import org.springframework.stereotype.Repository;

@Repository
public class UserProfileRepositoryImpl extends AbstractDao<UserProfile> implements UserProfileRepository {

    @Override
    public UserProfile create(UserProfile entity) {
        return super.create(entity);
    }

    @Override
    public UserProfile update(UserProfile entity) {
        return super.update(entity);
    }

    @Override
    public UserProfile read(Long id) {
        return super.read(id);
    }

    @Override
    public UserProfile delete(Long entityId) {
        return super.delete(entityId);
    }
}
