package com.test.project.security.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.project.dto.UserProfileDto;
import com.test.project.security.model.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String token;
    private List<RoleDto> roles;
    private UserProfileDto profile;
}
