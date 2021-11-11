package com.test.project.entity;

import lombok.*;

@Data
public class User {
    private Long id;
    private String email;
    private String password;
    private UserProfile profile;
}
