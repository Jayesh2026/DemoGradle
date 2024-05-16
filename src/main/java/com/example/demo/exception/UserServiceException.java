package com.example.demo.exception;


public class UserServiceException extends RuntimeException{
    
    public UserServiceException(String message){
        super(message);
    }

    public UserServiceException(String message, Exception exception) {
        super(message, exception);
    }
}
