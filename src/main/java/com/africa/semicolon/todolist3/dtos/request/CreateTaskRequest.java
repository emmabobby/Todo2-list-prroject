package com.africa.semicolon.todolist3.dtos.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Setter
@Getter
public class CreateTaskRequest {
    private String UserId;
    private String title;
    private String body;
    private LocalDateTime time;
}
