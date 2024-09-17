package com.africa.semicolon.todolist3.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateLoginRequest {
    private String id;
    private String username;
    private String password;
}
