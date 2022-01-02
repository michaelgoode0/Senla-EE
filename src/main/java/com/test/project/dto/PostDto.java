package com.test.project.dto;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PostDto {
    private Long id;
    @NotEmpty(message = "zaebalo")
    private String text;
}
