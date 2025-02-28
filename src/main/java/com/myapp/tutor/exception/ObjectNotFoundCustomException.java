package com.myapp.tutor.exception;

public class ObjectNotFoundCustomException extends RuntimeException{
    public ObjectNotFoundCustomException(String message) {
        super(message);
    }
}
