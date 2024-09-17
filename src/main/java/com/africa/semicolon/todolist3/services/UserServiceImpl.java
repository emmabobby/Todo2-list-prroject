package com.africa.semicolon.todolist3.services;

import com.africa.semicolon.todolist3.data.models.User;
import com.africa.semicolon.todolist3.data.repositories.UserRepository;
import com.africa.semicolon.todolist3.dtos.request.*;
import com.africa.semicolon.todolist3.dtos.response.*;
import com.africa.semicolon.todolist3.exceptions.InvalidPasswordException;
import com.africa.semicolon.todolist3.exceptions.UserAlreadyExistException;
import com.africa.semicolon.todolist3.exceptions.UserDoesNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final TaskService taskService;

    @Override
    public CreateRegistrationResponse register(CreateRegistrationRequest createRegistrationRequest) {
        if(userExists(createRegistrationRequest.getUsername()))
            throw new UserAlreadyExistException("user already exist");
        User user = new User();
        user.setUsername(createRegistrationRequest.getUsername());
        user.setPassword(createRegistrationRequest.getPassword());
        user = userRepository.save(user);

        CreateRegistrationResponse createRegistrationResponse = new CreateRegistrationResponse();
        createRegistrationResponse.setMessage("successfully created");
        createRegistrationResponse.setId(user.getId());
        createRegistrationResponse.setUsername(user.getUsername());
        return createRegistrationResponse;

    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(()->new UserDoesNotExistException("user"+ username +"does not exist"));
    }

    @Override
    public CreateLoginResponse login(CreateLoginRequest createLoginRequest) {
        Optional<User> foundUser = userRepository.findByUsername(createLoginRequest.getUsername());
        if(foundUser.isEmpty()) {
            throw new UserDoesNotExistException("user" + createLoginRequest.getUsername() + "does not exist");
        }
            User user = foundUser.get();
        if(!user.getPassword().equals(createLoginRequest.getPassword())) {
            throw new InvalidPasswordException("invalid password");
        }

            CreateLoginResponse createLoginResponse = new CreateLoginResponse();
            createLoginResponse.setMessage("Login Successful");
            return createLoginResponse;
        }

    @Override
    public CreateLogoutResponse logout(CreateLogoutRequest createLogoutRequest) {
        Optional<User> foundUser = userRepository.findByUsername(createLogoutRequest.getUsername());
        if(foundUser.isEmpty()) {
            throw new UserDoesNotExistException("user" + createLogoutRequest.getUsername() + "does not exist");
        }
        CreateLogoutResponse createLogoutResponse = new CreateLogoutResponse();
        createLogoutResponse.setMessage("logged out");
        return createLogoutResponse;
    }

    @Override
    public UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest) {
        Optional<User> foundUser = userRepository.findByUsername(updateUserRequest.getOldUsername());
        if(foundUser.isPresent()) {
            User user = foundUser.get();
            user.setUsername(updateUserRequest.getNewUsername());
            user.setPassword(updateUserRequest.getNewPassword());
            userRepository.save(user);
            UpdateUserResponse updateUserResponse = new UpdateUserResponse();
            updateUserResponse.setMessage("successfully updated");
            return updateUserResponse;
        }




        return null;
    }

    @Override
    public void deleteUser(String username) {
        User foundUser = findUserByUsername(username);
        userRepository.delete(foundUser);


    }

    @Override
    public CreateTaskResponse createTask(CreateTaskRequest request) {
        return taskService.createTask(request);
    }


    private boolean userExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }


}
