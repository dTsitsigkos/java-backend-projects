package com.example.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.backend.dto.CreateUserRequest;
import com.example.backend.dto.UpdateUserRequest;
import com.example.backend.dto.UserResponse;
import com.example.backend.exception.UserNotFoundException;
import com.example.backend.model.User;
import com.example.backend.repository.User_Repository;

@Service 
public class UserService {
    
    private final User_Repository userRepository;

    public UserService(User_Repository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUser(long id){
        
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public UserResponse createUser(CreateUserRequest request){
        User user = new User(request.getId(), request.getName());

        User savedUser = userRepository.save(user);

        return new UserResponse(savedUser.getId(),savedUser.getName());
    }

    public User updateUser(Long id, UpdateUserRequest request){
        
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        user.setName(request.getName());
        return userRepository.save(user);
    }

    public void deleteUser(long id){
        User user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));

        userRepository.delete(user);
    }

}
