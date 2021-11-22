package com.test.project.service;

import com.test.project.api.repository.UserRepository;
import com.test.project.api.service.UserService;
import com.test.project.dto.UserDto;
import com.test.project.dto.UserProfileDto;
import com.test.project.entity.User;
import com.test.project.dao.UserRepositoryImpl;
import com.test.project.entity.UserProfile;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Override
    @Transactional
    public UserDto create(UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        User response = userRepository.create(user);
        return mapper.map(response, UserDto.class);
    }

    @Override
    @Transactional
    public UserDto update(UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        User response = userRepository.update(user);
        if(response!=null){
            return mapper.map(response,UserDto.class);
        }
        return null;
    }

    @Override
    @Transactional
    public UserDto read(Long id) {
        userRepository.setClazz(User.class);
        User response = userRepository.read(id);
        if(response!=null){
            return mapper.map(response,UserDto.class);
        }
        return null;
    }

    @Override
    @Transactional
    public UserDto delete(Long id) {
        User response=userRepository.delete(id);
        if(response!=null){
            return mapper.map(response,UserDto.class);
        }
        return null;
    }

}
