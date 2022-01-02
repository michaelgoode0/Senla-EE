package com.test.project.security.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
public class LoginDto {
    private String username;
    private String password;
}
