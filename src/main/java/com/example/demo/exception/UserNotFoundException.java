package com.example.demo.exception;

public class UserNotFoundException extends UserServiceException {
    
    public UserNotFoundException(String messege){
        super(messege);
    }
}
