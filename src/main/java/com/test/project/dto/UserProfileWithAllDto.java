package com.test.project.dto;

import com.test.project.security.dto.UserDto;
import lombok.Data;

import java.util.List;

@Data
public class UserProfileWithAllDto {
    private Long id;
    private String firstname;
    private String surname;
    private String town;
    private Long phoneNumber;
    private List<PostWithAllDto> posts;
    private List<ReactionWithAllDto> reactions;
    private UserDto user;
}
