package com.test.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private UserProfileDto profile;
}
