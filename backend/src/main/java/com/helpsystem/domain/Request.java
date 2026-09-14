package com.helpsystem.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Class for Requests
 * It's how users can ask for help on the platform
 */

@Entity
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String question;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    private String name;
    private String status;
    private LocalDateTime creationDate;

    protected Request() {
    }

    public Request(String title, String question, Department department, String name, String status,
                   LocalDateTime creationDate) {

        if (title == null || question == null || department == null || name == null || status == null ||
                creationDate == null)
            throw new IllegalArgumentException("All fields must be properly filled out");

        this.title = title;
        this.question = question;
        this.department = department;
        this.name = name;
        this.status = status;
        this.creationDate = creationDate;
    }

    //Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getQuestion() {
        return question;
    }

    public Department getDepartment() {
        return department;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    //Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}