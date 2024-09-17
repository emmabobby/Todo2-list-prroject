package com.africa.semicolon.todolist3.dtos.response;

import com.africa.semicolon.todolist3.data.models.TaskStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTaskResponse {
    private String id;
    private String title;
    private String body;
    private TaskStatus status;
    private String userId;
}
