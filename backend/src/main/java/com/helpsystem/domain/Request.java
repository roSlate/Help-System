package com.helpsystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
    private String department;
    private String name;
    private String status;
    private LocalDateTime creationDate;

    protected Request() {
    }

    public Request(String title, String question, String department, String name, String status,
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

    public String getDepartment() {
        return department;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }
}