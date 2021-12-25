package com.test.project.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

@Data
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class ReactionDto {
    private Long id;
    private Boolean reaction;
    private UserProfileDto profile;
    private PostDto post;
}
