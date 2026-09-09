package com.helpsystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Class for a User
 * User can be an Administrator or a regular (common) user
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

    //Empty, required by JPA
    protected User() {
    }

    //Constructor
    public User(String name, String email, String password, String department) {

        if (name == null || email == null || password == null || department == null)
            throw new IllegalArgumentException("All fields are mandatory for registration");

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
