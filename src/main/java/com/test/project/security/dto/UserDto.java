package com.test.project.security.dto;

import com.test.project.dto.UserProfileDto;
import com.test.project.dto.UserProfileWithAllDto;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String username;
}
