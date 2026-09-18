package com.example.backend.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import org.springframework.test.web.servlet.MockMvc;

import com.example.backend.model.User;
import com.example.backend.repository.User_Repository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest 
@AutoConfigureMockMvc
public class UserControllerTest {
    
    @Autowired
    private User_Repository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();

        userRepository.save(new User(1, "Alice"));
        userRepository.save(new User(2, "Bob"));
    }

    @Test 
    void getUsers_returns200() throws Exception{
        mockMvc.perform(get("/users")).andExpect(status().isOk());
    }

    @Test
    void getUsers_returnsUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Alice"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Bob"));
    }

    @Test
    void getUser_returnsUser() throws Exception {
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Alice"));
    }

    @Test
    void getUser_whenNotFound_returns404() throws Exception {
        mockMvc.perform(get("/users/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createUser_whenInvalid_returns400() throws Exception {

        String request = """
            {
                "id": 0,
                "name": ""
            }
            """;

        mockMvc.perform(
                    post("/users")
                        .contentType(APPLICATION_JSON)
                        .content(request)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("Validation failed"));
    }
}
