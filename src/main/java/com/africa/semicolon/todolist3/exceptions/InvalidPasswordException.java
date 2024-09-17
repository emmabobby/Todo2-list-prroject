package com.africa.semicolon.todolist3.exceptions;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String message) {
        super("invalidPassword");
    }
}
