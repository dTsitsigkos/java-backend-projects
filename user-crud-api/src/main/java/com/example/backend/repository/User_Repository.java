package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backend.model.User;

public interface User_Repository extends JpaRepository<User,Long> {

    
}
