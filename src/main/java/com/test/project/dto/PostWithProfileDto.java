package com.test.project.dto;


import lombok.Data;

@Data
public class PostWithProfileDto {
    private Long id;
    private String text;
    private UserProfileWithAllDto userProfile;
}
