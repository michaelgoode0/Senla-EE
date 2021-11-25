package com.test.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ReactionDto {

    private Long id;
    private Boolean reaction;
    private UserProfileDto profile;
    private PostDto post;
}
