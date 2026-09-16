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

    private String answer;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request request;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    protected Reply() {
    }

    public Reply(String answer, Request request, User user, Department department) {

        if (answer == null || request == null || user == null || department == null)
            throw new IllegalArgumentException("All fields must be properly filled out");

        this.answer = answer;
        this.request = request;
        this.user = user;
        this.department = department;
    }

    //Getters
    public int getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }

    public User getUser() {
        return user;
    }
    public Request getRequest() {
        return request;
    }
    public Department getDepartment() {
        return department;
    }

    //Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void setRequest(Request request) {
        this.request = request;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }
}