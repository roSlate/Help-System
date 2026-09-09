package com.helpsystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Class for Reply
 * It's how users can answer requests on the platform
 */

@Entity
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String question;
    private String answer;
    private String status;
    private String name;
    private String department;

    protected Reply() {
    }

    public Reply(String title, String question, String answer,
                 String status, String name, String department) {

        if (title == null || question == null || answer == null || name == null || department == null)
            throw new IllegalArgumentException("All fields must be properly filled out");

        this.title = title;
        this.question = question;
        this.answer = answer;
        this.status = status;
        this.name = name;
        this.department = department;
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

    public String getAnswer() {
        return answer;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }
}