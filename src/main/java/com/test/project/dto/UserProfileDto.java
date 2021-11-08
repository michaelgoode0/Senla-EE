package com.test.project.dto;

import com.test.project.entity.Post;
import lombok.Data;

import java.util.List;

@Data
public class UserProfileDto {
    private Long id;
    private String name;
    private List<Post> posts;
}
