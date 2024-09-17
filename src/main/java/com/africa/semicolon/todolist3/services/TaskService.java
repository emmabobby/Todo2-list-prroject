package com.africa.semicolon.todolist3.services;

import com.africa.semicolon.todolist3.dtos.request.CreateTaskRequest;
import com.africa.semicolon.todolist3.dtos.response.CreateTaskResponse;

public interface TaskService {
    CreateTaskResponse createTask(CreateTaskRequest request);
}
