package com.example.user_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.user_service.model.User;
import com.example.user_service.repository.UserRepository;

@Service 
public class UserService {
    private final UserRepository userRepository;


    public UserService (UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }


    public User createUser(User user){
        return userRepository.save(user);
    }

    public boolean deleteUser (Long id){
        if (!userRepository.existsById(id)){
            return false;
        }
            
        userRepository.deleteById(id);
        
        return true;
    }
}
