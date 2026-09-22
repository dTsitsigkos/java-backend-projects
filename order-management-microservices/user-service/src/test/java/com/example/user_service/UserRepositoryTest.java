package com.example.user_service;

import com.example.user_service.model.User;
import com.example.user_service.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldSaveAndFindUser() {
        User user = new User(null, "Alice", "alice@example.com");

        User savedUser = userRepository.save(user);

        assertNotNull(savedUser.getId());

        User foundUser = userRepository.findById(savedUser.getId())
                .orElse(null);

        assertNotNull(foundUser);
        assertEquals("Alice", foundUser.getName());
        assertEquals("alice@example.com", foundUser.getEmail());
    }
}