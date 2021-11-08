package com.test.project.dao;

import com.test.project.api.repository.UserProfileRepository;
import com.test.project.entity.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserProfileRepositoryImpl implements UserProfileRepository {
    private final List<UserProfile> userProfiles = new ArrayList<>();

    private List<UserProfile> getUserProfiles() {
        return userProfiles;
    }

    @Override
    public UserProfile create(UserProfile userProfile) {
        userProfiles.add(userProfile);
        return userProfile;
    }

    @Override
    public UserProfile update(UserProfile userProfile) {
        for (UserProfile i : getUserProfiles()) {
            if (i.getId().equals(userProfile.getId())) {
                int index = getUserProfiles().indexOf(i);
                getUserProfiles().set(index, userProfile);
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
        UserProfile user= getById(id);
        if(user==null) {
            return null;
        }
        getUserProfiles().remove(user);
        return user;
    }

    private UserProfile getById(long id){
        for(UserProfile user: getUserProfiles()){
            if(user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }
}
