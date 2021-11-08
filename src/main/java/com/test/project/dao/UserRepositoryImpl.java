package com.test.project.dao;

import com.test.project.api.repository.UserRepository;
import com.test.project.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final List<User> listOfUsers= new ArrayList<>();


    public List<User> getListOfUsers() {
        return listOfUsers;
    }

    public User getById(Long id){
        for(User user: listOfUsers){
            if(user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }

    @Override
    public User create(User user) {
        listOfUsers.add(user);
        return user;
    }

    @Override
    public User update(User user) {
        for (User i : getListOfUsers()) {
            if (i.getId().equals(user.getId())) {
                int index = getListOfUsers().indexOf(i);
                getListOfUsers().set(index, user);
                return user;
            }
        }
        return null;
    }

    @Override
    public User read(Long id) {
        return getById(id);
    }

    @Override
    public User delete(Long id) {
        User user= getById(id);
        if(user==null) {
            return null;
        }
        getListOfUsers().remove(user);
        return user;

    }
}
