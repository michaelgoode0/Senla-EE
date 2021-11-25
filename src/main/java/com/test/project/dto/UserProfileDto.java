package com.test.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.project.entity.Post;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
public class UserProfileDto {
    private Long id;
    private String name;
    private List<PostDto> posts;
}
