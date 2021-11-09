package com.test.project.dao;

import com.test.project.api.repository.UserProfileRepository;
import com.test.project.entity.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserProfileRepositoryImpl implements UserProfileRepository {
    private final List<UserProfile> userProfiles = new ArrayList<>();

    @Override
    public UserProfile create(UserProfile userProfile) {
        userProfiles.add(userProfile);
        return userProfile;
    }

    @Override
    public UserProfile update(UserProfile userProfile) {
        for (UserProfile i : userProfiles) {
            if (i.getId().equals(userProfile.getId())) {
                int index = userProfiles.indexOf(i);
                userProfiles.set(index, userProfile);
                return userProfile;
            }
        }
        return null;
    }

    @Override
    public UserProfile read(Long id) {
        return getById(id);
    }

    @Override
    public UserProfile delete(Long id) {
        Optional<UserProfile> user= Optional.of(getById(id));
        userProfiles.remove(user.orElse(null));
        return user.orElse(null);
    }

    private UserProfile getById(long id){
        return userProfiles.stream().filter(s->s.getId().equals(id)).findFirst().orElse(null);
    }
}
