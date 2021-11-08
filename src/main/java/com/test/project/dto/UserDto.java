package com.test.project.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private UserProfileDto profile;
}
