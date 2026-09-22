package com.example.user_service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.example.user_service.model.User;
import com.example.user_service.repository.UserRepository;
import com.example.user_service.service.UserService;

public class UserServiceTest {

    @Test
    void shouldCreateUser() {
        UserRepository userRepository = mock(UserRepository.class);
        UserService userService = new UserService(userRepository);

        User user = new User(null, "Dimitris", "dimitris@example.com");

        when(userRepository.save(user)).thenReturn(user);

        User result = userService.createUser(user);

        assertEquals(user, result);
        verify(userRepository).save(user);
    }

    @Test
    void shouldGetAllUsers() {
        UserRepository userRepository = mock(UserRepository.class);
        UserService userService = new UserService(userRepository);

        List<User> users = List.of(
                new User(1L, "Dimitris", "dimitris@example.com"),
                new User(2L, "John", "john@example.com")
        );

        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertEquals(2, result.size());
        verify(userRepository).findAll();
    }

    @Test
    void shouldGetUserById() {
        UserRepository userRepository = mock(UserRepository.class);
        UserService userService = new UserService(userRepository);

        User user = new User(1L, "Dimitris", "dimitris@example.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals("Dimitris", result.getName());
        verify(userRepository).findById(1L);
    }

    @Test
    void shouldReturnNullWhenUserDoesNotExist() {
        UserRepository userRepository = mock(UserRepository.class);
        UserService userService = new UserService(userRepository);

        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        User result = userService.getUserById(999L);

        assertNull(result);
        verify(userRepository).findById(999L);
    }

    @Test
    void shouldDeleteUser() {
        UserRepository userRepository = mock(UserRepository.class);
        UserService userService = new UserService(userRepository);

        when(userRepository.existsById(1L)).thenReturn(true);

        boolean result = userService.deleteUser(1L);

        assertTrue(result);
        verify(userRepository).existsById(1L);
        verify(userRepository).deleteById(1L);
    }

    @Test
    void shouldNotDeleteUserWhenUserDoesNotExist() {
        UserRepository userRepository = mock(UserRepository.class);
        UserService userService = new UserService(userRepository);

        when(userRepository.existsById(999L)).thenReturn(false);

        boolean result = userService.deleteUser(999L);

        assertFalse(result);
        verify(userRepository).existsById(999L);
        verify(userRepository, never()).deleteById(999L);
    }
    
}
