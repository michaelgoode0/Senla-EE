package com.test.project.security.dto;

import com.test.project.dto.UserProfileDto;
import lombok.Data;

import java.util.List;
@Data
public class UserWithAllDto {
    private Long id;
    private String username;
    private List<RoleDto> roles;
    private UserProfileDto profile;
}
