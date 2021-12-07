package com.test.project.security.service;

import com.test.project.security.api.repository.RoleRepository;
import com.test.project.security.api.repository.UserRepository;
import com.test.project.security.api.service.UserService;
import com.test.project.security.dto.LoginDto;
import com.test.project.security.dto.RoleDto;
import com.test.project.security.dto.UserDto;
import com.test.project.security.enums.RoleName;
import com.test.project.security.filter.TokenProvider;
import com.test.project.security.model.Role;
import com.test.project.security.model.User;
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
        User user = userRepository.loadUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    @Override
    @Transactional
    public UserDto signUp(LoginDto dto) {
        User user = new User();
        Role userRole = roleRepository.findRoleByName(RoleName.ROLE_USER);
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRoles(Collections.singletonList(userRole));
        userRepository.create(user);
        return mapper.map(user,UserDto.class);
    }

    @Override
    @Transactional
    public String signIn(LoginDto dto) {
        final UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword());
        final Authentication authentication = authenticationManager.getObject().authenticate(authenticationToken);
        String token = tokenProvider.createToken(authentication);
        User user = userRepository.loadUserByUsername(dto.getUsername());
        user.setToken(token);
        userRepository.update(user);
        return token;
    }

    @Override
    @Transactional
    public UserDto delete(Long id){
        User user = userRepository.delete(id);
        if(user!=null){
            return mapper.map(user,UserDto.class);
        }
        return null;
    }

}
