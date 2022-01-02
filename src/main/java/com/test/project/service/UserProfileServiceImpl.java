package com.test.project.service;

import com.test.project.api.repository.UserProfileRepository;
import com.test.project.api.service.UserProfileService;
import com.test.project.dto.UserProfileDto;
import com.test.project.dto.UserProfileWithAllDto;
import com.test.project.entity.UserProfile;
import com.test.project.security.api.repository.UserRepository;
import com.test.project.security.model.User;
import com.test.project.util.AuthNameHolder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Override
    @Transactional
    @PreAuthorize("@userProfileServiceImpl.read(#userProfileDto.id).user.username == authentication.name")
    public UserProfileDto update(UserProfileDto userProfileDto) {
        UserProfile userProfile = userProfileRepository.findById(userProfileDto.getId()).orElse(null);
        User user = userRepository.findUserByUsername(AuthNameHolder.getAuthUsername());
        mapper.map(userProfileDto,userProfile);
        userProfile.setUser(user);
        UserProfile response = userProfileRepository.save(userProfile);
        return mapper.map(response, UserProfileDto.class);

    }

    @Override
    @Transactional
    public UserProfileWithAllDto read(Long id) {
        UserProfile response = userProfileRepository.findById(id).orElse(null);
        return mapper.map(response, UserProfileWithAllDto.class);
    }

    @Override
    @Transactional
    @PreAuthorize("@userProfileServiceImpl.read(#id).user.username == authentication.name")
    public UserProfileWithAllDto delete(Long id) {
        UserProfileWithAllDto response = read(id);
        userProfileRepository.deleteById(id);
        return mapper.map(response, UserProfileWithAllDto.class);
    }
}
