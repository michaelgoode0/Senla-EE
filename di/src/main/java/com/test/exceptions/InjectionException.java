package com.test.exceptions;

public class InjectionException extends RuntimeException{

    public InjectionException(String message, Throwable cause){
        super(message,cause);
    }
    public InjectionException(String message){
        super(message);
    }
}
