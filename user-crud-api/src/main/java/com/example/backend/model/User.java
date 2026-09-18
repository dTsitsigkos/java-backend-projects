package com.example.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;


@Entity
@Table(name = "users") 
public class User {
    
    @Id
    private long id;
    
    private String name;
    
    public User(){

    }

    public User(long id, String name){
        this.id = id;
        this.name = name;
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
