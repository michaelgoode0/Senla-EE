package com.test.project.dto;

import lombok.Data;
import java.util.List;

@Data
public class UserProfileDto {
    private Long id;
    private String name;
    private List<PostDto> posts;
}
