package com.example.demo.exception;

public class ExistingUserException extends UserServiceException {
    
    public ExistingUserException(String message){
        super(message);
    }
}
