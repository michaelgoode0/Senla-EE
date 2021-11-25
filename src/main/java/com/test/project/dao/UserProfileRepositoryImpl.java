package com.test.project.dao;

import com.test.project.util.AbstractDao;
import com.test.project.api.repository.UserProfileRepository;
import com.test.project.entity.UserProfile;
import org.springframework.stereotype.Repository;

@Repository
public class UserProfileRepositoryImpl extends AbstractDao<UserProfile> implements UserProfileRepository {
}
