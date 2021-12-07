package com.test.project.security.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.project.security.enums.RoleName;
import com.test.project.security.model.User;
import lombok.Data;

import java.util.List;

@Data
public class RoleDto {
    private Long id;
    private RoleName role;
    @JsonIgnore
    private List<UserDto> users;
}
