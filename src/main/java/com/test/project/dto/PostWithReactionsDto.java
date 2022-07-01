package com.test.project.dto;

import lombok.Data;

import java.util.List;

@Data
public class PostWithReactionsDto {
    private Long id;
    private String text;
    private List<ReactionDto> reactions;
}
