package com.test.project.dto;

import lombok.Data;

import java.util.List;

@Data
public class HashtagDto {
    private Long id;
    private String value;
    private List<PostDto> posts;
}
