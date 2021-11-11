package com.test.project.entity;

import lombok.Data;

import java.util.List;

@Data
public class UserProfile {
    private Long id;
    private String name;
    private List<Post> posts;
}
