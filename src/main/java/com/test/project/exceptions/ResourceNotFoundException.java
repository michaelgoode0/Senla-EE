package com.test.project.exceptions;

public class ResourceNotFoundException extends RuntimeException  {
    public ResourceNotFoundException(String cause){
        super(cause);
    }
    public ResourceNotFoundException(String cause, Throwable e){
        super(cause, e);
    }
}
