package com.test.project.exceptions;

import lombok.Getter;

public class EntityNotFoundException extends RuntimeException{
    @Getter
    private Long id;

    public EntityNotFoundException(Long id) {
        this.id = id;
    }
}