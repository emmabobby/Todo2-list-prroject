package com.africa.semicolon.todolist3;

import com.africa.semicolon.todolist3.data.models.User;
import static org.junit.jupiter.api.Assertions.*;
import com.africa.semicolon.todolist3.data.repositories.UserRepository;
import com.africa.semicolon.todolist3.dtos.request.*;
import com.africa.semicolon.todolist3.dtos.response.*;
import com.africa.semicolon.todolist3.exceptions.UserAlreadyExistException;
import com.africa.semicolon.todolist3.services.TaskService;
import com.africa.semicolon.todolist3.services.UserService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class TodoList3ApplicationTests {
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void testThatUserCanRegister(){
        CreateRegistrationRequest createRegistrationRequest = new CreateRegistrationRequest();
        createRegistrationRequest.setUsername("emmanuel");
        createRegistrationRequest.setPassword("12345");
        userService.register(createRegistrationRequest);
        assertEquals(1, userRepository.count());

    }
    @Test
    public void testThatUserCanNotRegisterTwice(){
        CreateRegistrationRequest  createRegistrationRequest = new CreateRegistrationRequest();
        createRegistrationRequest.setUsername("emmanuel");
        createRegistrationRequest.setPassword("12345");
        userService.register(createRegistrationRequest);
        assertEquals(1, userRepository.count());

        CreateRegistrationRequest secondReistrationRequest = new CreateRegistrationRequest();
        secondReistrationRequest.setUsername("emmanuel");
        secondReistrationRequest.setPassword("12345");
        assertThrows(UserAlreadyExistException.class, ()->userService.register(secondReistrationRequest));
        assertEquals(1, userRepository.count());


    }

    @Test
    public void testThatUserCanLogin(){
        CreateRegistrationRequest createRegistrationRequest = new CreateRegistrationRequest();
        createRegistrationRequest.setUsername("emmanuel");
        createRegistrationRequest.setPassword("12345");
        userService.register(createRegistrationRequest);
        assertEquals(1, userRepository.count());

        CreateLoginRequest createLoginRequest = new CreateLoginRequest();
        createLoginRequest.setUsername("emmanuel");
        createLoginRequest.setPassword("12345");
        CreateLoginResponse response = userService.login(createLoginRequest);
        assertNotNull(response);

    }

    @Test
    public void testThatUserCanLogout(){
        CreateRegistrationRequest createRegistrationRequest = new CreateRegistrationRequest();
        createRegistrationRequest.setUsername("emmanuel");
        createRegistrationRequest.setPassword("12345");
        userService.register(createRegistrationRequest);
        assertEquals(1, userRepository.count());

        CreateLoginRequest createLoginRequest = new CreateLoginRequest();
        createLoginRequest.setUsername("emmanuel");
        createLoginRequest.setPassword("12345");
        CreateLoginResponse response = userService.login(createLoginRequest);
        System.out.println(response.getMessage());
        assertNotNull(response);

        CreateLogoutRequest createLogoutRequest = new CreateLogoutRequest();
        createLogoutRequest.setUsername("emmanuel");
        CreateLogoutResponse logoutResponse = userService.logout(createLogoutRequest);
        assertNotNull(logoutResponse);
        assertEquals(logoutResponse.getMessage(), "logged out");

    }

    @Test
    public void testThatUserCanUpdate(){
        CreateRegistrationRequest createRegistrationRequest = new CreateRegistrationRequest();
        createRegistrationRequest.setUsername("emmanuel");
        createRegistrationRequest.setPassword("12345");
        userService.register(createRegistrationRequest);
        assertEquals(1, userRepository.count());

        CreateLoginRequest createLoginRequest = new CreateLoginRequest();
        createLoginRequest.setUsername("emmanuel");
        createLoginRequest.setPassword("12345");
        CreateLoginResponse response = userService.login(createLoginRequest);
        assertNotNull(response);

        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setOldPassword("12345");
        updateUserRequest.setNewPassword("2222");
        updateUserRequest.setOldUsername("emmanuel");
        updateUserRequest.setNewUsername("emmanuel2");

        UpdateUserResponse updateUserResponse = userService.updateUser(updateUserRequest);

        assertEquals(updateUserResponse.getMessage(),"successfully updated");

    }

    @Test
    public void testThatUserCanBeDeleted(){
        CreateRegistrationRequest createRegistrationRequest = new CreateRegistrationRequest();
        createRegistrationRequest.setUsername("emmanuel");
        createRegistrationRequest.setPassword("12345");
        userService.register(createRegistrationRequest);
        assertEquals(1, userRepository.count());

        CreateLoginRequest createLoginRequest = new CreateLoginRequest();
        createLoginRequest.setUsername("emmanuel");
        createLoginRequest.setPassword("12345");
        CreateLoginResponse response = userService.login(createLoginRequest);
        assertNotNull(response);

        userService.deleteUser("emmanuel");
        assertEquals(0, userRepository.count());
    }
    @Test
    public void testThatUserCanCreateTask(){
        CreateRegistrationRequest createRegistrationRequest = new CreateRegistrationRequest();
        createRegistrationRequest.setUsername("emmanuel");
        createRegistrationRequest.setPassword("12345");
        CreateRegistrationResponse userResponse =userService.register(createRegistrationRequest);
        assertEquals(1, userRepository.count());

        CreateLoginRequest createLoginRequest = new CreateLoginRequest();
        createLoginRequest.setUsername("emmanuel");
        createLoginRequest.setPassword("12345");

        CreateTaskRequest request = new CreateTaskRequest();
        request.setBody("body");
        request.setTitle("Title");
        request.setUserId(userResponse.getId());
        request.setTime(LocalDateTime.now());

        CreateTaskResponse response = userService.createTask(request);
        assertNotNull(response);
        assertEquals(response.getUserId(),userResponse.getId());
    }



}






