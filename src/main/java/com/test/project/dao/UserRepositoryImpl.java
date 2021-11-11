package com.test.project.dao;

import com.test.project.api.repository.UserRepository;
import com.test.project.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final List<User> listOfUsers= new ArrayList<>();

    public Optional<User> getById(Long id){
        return listOfUsers.stream().filter(s->s.getId().equals(id)).findFirst();
    }

    @Override
    public User create(User user) {
        listOfUsers.add(user);
        return user;
    }

    @Override
    public User update(User user) {
        for (User i : listOfUsers) {
            if (i.getId().equals(user.getId())) {
                int index = listOfUsers.indexOf(i);
                listOfUsers.set(index, user);
                return user;
            }
        }
        return null;
    }

    @Override
    public User read(Long id) {
        return getById(id).orElse(null);
    }

    @Override
    public User delete(Long id) {
        Optional<User> user= getById(id);
        listOfUsers.remove(user.orElse(null));
        return user.orElse(null);
    }
}
