package com.test.project.entity;

import lombok.Data;

@Data
public class Post {
    private Long id;
    private String text;
    private UserProfile profile;
}
