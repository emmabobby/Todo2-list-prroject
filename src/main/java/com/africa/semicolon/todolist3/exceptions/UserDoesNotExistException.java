package com.africa.semicolon.todolist3.exceptions;

public class UserDoesNotExistException extends RuntimeException {
    public UserDoesNotExistException(String message) {
        super("UserDoesNotExistException: " + message);
    }
}
