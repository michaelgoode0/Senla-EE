package com.test.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class PostCommentDto {

    private Long id;
    private String text;
    private UserProfileDto profile;
    private PostDto post;
}
