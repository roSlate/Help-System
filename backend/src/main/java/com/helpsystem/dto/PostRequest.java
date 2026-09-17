package com.helpsystem.dto;

import com.helpsystem.domain.Reply;
import com.helpsystem.domain.User;

import java.util.List;

public class PostRequest {

    private String title;
    private String question;
    private User user;
    private String status;
    private List<Reply> replies;

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public String getQuestion() {return question;}
    public void setQuestion(String question) {this.question = question;}

    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}

    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}

    public List<Reply> getReplies() {return replies;}
    public void setReplies(List<Reply> replies) {this.replies = replies;}
}
