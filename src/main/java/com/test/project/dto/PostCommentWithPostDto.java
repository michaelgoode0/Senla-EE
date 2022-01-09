package com.test.project.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class PostCommentWithPostDto {
    private Long id;
    @NotEmpty(message = "Post comment can not be empty")
    private String text;
    private PostDto post;
}
