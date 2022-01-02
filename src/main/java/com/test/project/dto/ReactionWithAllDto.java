package com.test.project.dto;

import lombok.Data;

@Data
public class ReactionWithAllDto {
    private Long id;
    private Boolean reaction;
    private UserProfileWithAllDto profile;
    private PostWithAllDto post;
}
