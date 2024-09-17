package com.africa.semicolon.todolist3.web;

import com.africa.semicolon.todolist3.dtos.request.CreateRegistrationRequest;
import com.africa.semicolon.todolist3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody  CreateRegistrationRequest request){
        try{
            var result = userService.register(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
