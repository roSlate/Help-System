package com.helpsystem.domain;

import jakarta.persistence.*;

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

    @Column(unique = true)
    private String email;

    private String password;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    //Empty, required by JPA
    protected User() {
    }

    //Constructor
    public User(String name, String email, String password, Department department) {

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

    public Department getDepartment() {
        return department;
    }

    public String getPassword() {
        return password;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
