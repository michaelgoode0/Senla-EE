package com.test.project.security.service;

import com.test.project.entity.User;
import com.test.project.entity.UserProfile;
import com.test.project.exceptions.ResourceNotFoundException;
import com.test.project.security.api.repository.RoleRepository;
import com.test.project.security.api.repository.UserRepository;
import com.test.project.security.api.service.UserService;
import com.test.project.security.dto.LoginDto;
import com.test.project.security.dto.UserWithAllDto;
import com.test.project.security.enums.RoleName;
import com.test.project.security.filter.TokenProvider;
import com.test.project.security.model.Role;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManager;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ModelMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username).orElseThrow(()->new ResourceNotFoundException("User not found"));
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }


    @Override
    @Transactional
    public UserWithAllDto signUp(LoginDto dto,RoleName roleName) {
        User user = new User();
        UserProfile userProfile = new UserProfile();
        Role userRole = roleRepository.findRoleByRoleName(roleName);
        user.setUsername(dto.getUsername());
        userProfile.setFirstname(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRoles(Collections.singletonList(userRole));
        user.setProfile(userProfile);
        userRepository.save(user);
        return mapper.map(user,UserWithAllDto.class);
    }

    @Override
    @Transactional
    public String signIn(LoginDto dto) {
        final UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword());
        final Authentication authentication = authenticationManager.getObject().authenticate(authenticationToken);
        return tokenProvider.createToken(authentication);
    }

    @Override
    @Transactional
    public void delete(Long id){
        userRepository.deleteById(id);
    }
    @Override
    @Transactional
    public UserWithAllDto loadByUsername(String username) {
        User user = userRepository.findUserByUsername(username).orElse(new User());
        return mapper.map(user,UserWithAllDto.class);

    }

}
