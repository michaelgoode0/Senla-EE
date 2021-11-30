package com.test.project.exceptions;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse errorResponse= new ErrorResponse(HttpStatus.BAD_REQUEST,"Bad request", details);
        return new ResponseEntity<>(errorResponse,new HttpHeaders(),errorResponse.getStatus());
    }

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public final ResponseEntity<ErrorResponse> handleUserNotFoundException(HttpClientErrorException.NotFound ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse errorResponse= new ErrorResponse(HttpStatus.NOT_FOUND,"Not Found", details);
        return new ResponseEntity<>(errorResponse,new HttpHeaders(),errorResponse.getStatus());
    }

}