package com.example.backend.dto;

import jakarta.validation.constraints.NotBlank;

public class UpdateUserRequest {
    
    @NotBlank 
    private String name;

    public UpdateUserRequest(){

    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
