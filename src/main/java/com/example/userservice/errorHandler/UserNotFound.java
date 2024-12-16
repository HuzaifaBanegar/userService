package com.example.userservice.errorHandler;

public class UserNotFound extends Exception {
    public UserNotFound(String message) {
        super(message);
    }
}
