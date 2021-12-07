package com.test.project.security.dto;

import com.test.project.dto.UserProfileDto;
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
