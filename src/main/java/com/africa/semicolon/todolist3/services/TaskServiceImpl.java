package com.africa.semicolon.todolist3.services;

import com.africa.semicolon.todolist3.data.models.Task;
import com.africa.semicolon.todolist3.data.repositories.TaskRepository;
import com.africa.semicolon.todolist3.dtos.request.CreateTaskRequest;
import com.africa.semicolon.todolist3.dtos.response.CreateTaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{
    private final TaskRepository repository;
    @Override
    public CreateTaskResponse createTask(CreateTaskRequest request) {
        Task task = new Task();
        task.setBody(request.getBody());
        task.setTitle(request.getTitle());
        task.setDueDate(request.getTime());
        task = repository.save(task);
        CreateTaskResponse response = new CreateTaskResponse();
        response.setId(task.getId());
        response.setBody(task.getBody());
        response.setTitle(task.getTitle());
        response.setUserId(request.getUserId());
        return response;
    }
}
