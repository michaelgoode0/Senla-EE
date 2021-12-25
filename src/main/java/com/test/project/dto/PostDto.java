package com.test.project.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.test.project.entity.Hashtag;
import lombok.Data;

import java.util.List;

@Data
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class PostDto {
    private Long id;
    private String text;
    private UserProfileDto profile;
    private List<PostCommentDto> comments;
    private List<ReactionDto> reactions;
}
