package com.africa.semicolon.todolist3.exceptions;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String message) {
        super("User is already exist");
    }

}
