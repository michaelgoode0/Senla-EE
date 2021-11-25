package com.test.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Data
public class PostDto {
    private Long id;
    private String text;
    @JsonIgnore
    private UserProfileDto profile;
    private List<PostCommentDto> comments;
    private List<ReactionDto> reactions;
}
