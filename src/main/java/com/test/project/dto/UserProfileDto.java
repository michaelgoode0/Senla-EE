package com.test.project.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserProfileDto {
    private Long id;
    @NotEmpty(message = "Profiles name can not be empty")
    private String firstname;
    private String surname;
    private String town;
    private Long phoneNumber;
}
