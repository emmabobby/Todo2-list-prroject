package com.africa.semicolon.todolist3.data.repositories;


import com.africa.semicolon.todolist3.data.models.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {
}
