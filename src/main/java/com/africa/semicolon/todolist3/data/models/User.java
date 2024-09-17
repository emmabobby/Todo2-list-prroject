package com.africa.semicolon.todolist3.data.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Getter
@Setter
public class User {
    private String id;
    private String username;
    private String password;
    private List<Task> task = new ArrayList<>();
    private boolean isLoggedIn = false;

}
