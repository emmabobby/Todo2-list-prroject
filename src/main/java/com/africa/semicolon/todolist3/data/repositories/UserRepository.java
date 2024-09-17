package com.africa.semicolon.todolist3.data.repositories;

import com.africa.semicolon.todolist3.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUsername(String username);
}
