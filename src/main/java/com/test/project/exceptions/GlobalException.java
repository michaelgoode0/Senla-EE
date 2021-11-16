package com.test.project.exceptions;

public class GlobalException extends RuntimeException{
    public GlobalException(String cause){
        super(cause);
    }
    public GlobalException(String cause, Throwable e){
        super(cause, e);
    }
}
