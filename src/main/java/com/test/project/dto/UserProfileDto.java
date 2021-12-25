package com.test.project.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.test.project.entity.Reaction;
import lombok.Data;
import java.util.List;

@Data
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class UserProfileDto {
    private Long id;
    private String name;
    private List<PostDto> posts;
    private ReactionDto reaction;
}
