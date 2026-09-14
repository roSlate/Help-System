package com.helpsystem.domain;

import jakarta.persistence.*;

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


    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    protected Reply() {
    }

    public Reply(String title, String question, String answer,
                 String status, String name, Department department) {

        if (title == null || question == null || answer == null || status == null || name == null || department == null)
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

    public Department getDepartment() {
        return department;
    }

    //Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}