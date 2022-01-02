package com.test.project.dto;

import lombok.Data;

@Data
public class PostCommentWithAllDto {
    private Long id;
    private String text;
    private UserProfileWithUserDto profile;
    private PostDto post;
}
