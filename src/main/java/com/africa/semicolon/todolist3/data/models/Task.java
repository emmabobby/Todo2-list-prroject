package com.africa.semicolon.todolist3.data.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Getter
@Setter
public class Task {
    private String id;
    private String title;
    private String body;
    private TaskStatus isComplete;
    private LocalDateTime dueDate;
    private String userId;
}
