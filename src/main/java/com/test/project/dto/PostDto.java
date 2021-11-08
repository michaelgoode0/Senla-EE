package com.test.project.dto;

import lombok.Data;

@Data
public class PostDto {
    private Long id;
    private String text;
    private UserProfileDto profile;
}
