package com.test.project.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class UserProfileDto {
    private Long id;
    @NotEmpty(message = "Profiles name can not be empty")
    private String firstname;
    @NotEmpty(message = "Profiles name can not be empty")
    private String surname;
    @NotEmpty(message = "Profiles name can not be empty")
    private String town;
    @Min(5)
    private Long phoneNumber;
}
