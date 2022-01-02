package com.test.project.dto;

import com.test.project.security.dto.UserDto;
import lombok.Data;

@Data
public class UserProfileWithUserDto {
    private Long id;
    private String firstname;
    private String surname;
    private String town;
    private Long phoneNumber;
    private UserDto user;
}
