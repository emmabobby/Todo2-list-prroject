package com.africa.semicolon.todolist3.services;

import com.africa.semicolon.todolist3.data.models.User;
import com.africa.semicolon.todolist3.dtos.request.*;
import com.africa.semicolon.todolist3.dtos.response.*;

public interface UserService {
    CreateRegistrationResponse register(CreateRegistrationRequest createRegistrationRequest);
    User findUserByUsername(String username);

    CreateLoginResponse login(CreateLoginRequest createLoginRequest);
    CreateLogoutResponse logout(CreateLogoutRequest createLogoutRequest);
    UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest);
    void deleteUser(String username);
    CreateTaskResponse createTask(CreateTaskRequest request);
}
