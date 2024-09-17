package com.africa.semicolon.todolist3.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {
    private String id;
    private String oldUsername;
    private String newUsername;
    private String newPassword;
    private String oldPassword;
}
