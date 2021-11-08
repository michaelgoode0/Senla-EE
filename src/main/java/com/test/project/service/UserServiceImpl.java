package com.test.project.service;

import com.test.project.api.service.UserService;
import com.test.project.dto.UserDto;
import com.test.project.entity.User;
import com.test.project.dao.UserRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepositoryImpl userRepository;
    @Autowired
    private final ModelMapper mapper;

    @Override
    public UserDto create(UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        User response = userRepository.create(user);
        return mapper.map(response, UserDto.class);
    }

    @Override
    public UserDto update(UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        User response = userRepository.update(user);
        if (response != null) {
            return mapper.map(response, UserDto.class);
        }
        return null;
    }

    @Override
    public UserDto read(Long id) {
        User response = userRepository.read(id);
        if (response != null) {
            return mapper.map(response, UserDto.class);
        }
        return null;
    }

    @Override
    public UserDto delete(Long id) {
        User response=userRepository.delete(id);
        if (response != null) {
            return mapper.map(response, UserDto.class);
        }
        return null;
    }

}
