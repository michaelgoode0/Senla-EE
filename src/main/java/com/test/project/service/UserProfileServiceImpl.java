package com.test.project.service;

import com.test.project.api.service.UserProfileService;
import com.test.project.dto.UserProfileDto;
import com.test.project.entity.UserProfile;
import com.test.project.dao.UserProfileRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private final UserProfileRepositoryImpl userProfileRepository;
    @Autowired
    private final ModelMapper mapper;

    @Override
    public UserProfileDto create(UserProfileDto userProfileDto) {
        UserProfile user = mapper.map(userProfileDto, UserProfile.class);
        UserProfile response = userProfileRepository.create(user);
        return mapper.map(response, UserProfileDto.class);
    }

    @Override
    public UserProfileDto update(UserProfileDto userProfileDto) {
        UserProfile user = mapper.map(userProfileDto, UserProfile.class);
        UserProfile response = userProfileRepository.update(user);
        if (response != null) {
            return mapper.map(response, UserProfileDto.class);
        }
        return null;
    }

    @Override
    public UserProfileDto read(Long id) {
        UserProfile response = userProfileRepository.read(id);
        if (response != null) {
            return mapper.map(response, UserProfileDto.class);
        }
        return null;
    }

    @Override
    public UserProfileDto delete(Long id) {
        UserProfile response=userProfileRepository.delete(id);
        if (response != null) {
            return mapper.map(response, UserProfileDto.class);
        }
        return null;
    }
}
