package com.example.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class CreateUserRequest {
    @Min(1)
    private long id;

    @NotBlank 
    private String name;

    public CreateUserRequest(){

    }

    public long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public void setId(long id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }
}
