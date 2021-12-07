package com.test.project.dto;

import lombok.Data;

@Data
public class PostCommentDto {

    private Long id;
    private String text;
    private UserProfileDto profile;
    private PostDto post;
}
