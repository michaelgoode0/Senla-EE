package com.test.project.exceptions;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@RequiredArgsConstructor
public class ErrorResponse {
    private final HttpStatus status;
    private final String message;
    private final List<String> details;
}