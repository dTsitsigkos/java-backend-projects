package com.example.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.CreateUserRequest;
import com.example.backend.dto.UpdateUserRequest;
import com.example.backend.dto.UserResponse;
import com.example.backend.model.User;
import com.example.backend.service.UserService;

import jakarta.validation.Valid;




@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserResponse> getUsers() {
        List<User> users = userService.getUsers();

        return users.stream().map(user->new UserResponse(user.getId(),user.getName())).toList();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable long id) {
        return userService.getUser(id);
    }

    @PostMapping("/users")
    @ResponseStatus (HttpStatus.CREATED)
    public UserResponse createUser(@Valid @RequestBody CreateUserRequest user) {
        return userService.createUser(user);
    }
    
    @PutMapping("/users/{id}")
    public UserResponse updateUser(@PathVariable long id, @RequestBody UpdateUserRequest request) {
        User user = userService.updateUser(id, request);

        return new UserResponse(user.getId(), user.getName());
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deteleUser(@PathVariable long id){
        userService.deleteUser(id);
    }
    

}
