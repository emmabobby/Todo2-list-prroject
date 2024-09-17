package com.africa.semicolon.todolist3.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CreateLogoutRequest {
    private String username;
    private boolean isLogin = false;
}
