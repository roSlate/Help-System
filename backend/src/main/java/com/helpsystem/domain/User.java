package com.helpsystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Class for a User
 * User can be a normal user or a regular user
 */

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String email;
    private String password;
    private String department;

    //Constructor
    public User(int id, String name, String email, String password, String department) {

        if (id < 0 || name == null || email == null || password == null || department == null)
            throw new IllegalArgumentException("All fields are mandatory for registration");

        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.department = department;
    }

    //Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }
}
