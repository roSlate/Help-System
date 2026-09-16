package com.helpsystem.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ReplyTest {

    @Test
    void shouldCreateReply() {

        //Arrange
        Department department = new Department("Sales");
        User user = new User("John","johnnyboy@mail.com","password", department);
        Request request = new Request("I need help as well", "What is your problem?", department, user,
                "Open", LocalDateTime.now());

        Reply reply = new Reply("I'm not sure", request, user, department);

        //Act + assert
        assertThat(reply.getRequest().getTitle()).isEqualTo("I need help as well");
        assertThat(reply.getRequest().getQuestion()).isEqualTo("What is your problem?");
        assertThat(reply.getAnswer()).isEqualTo("I'm not sure");
        assertThat(reply.getRequest().getStatus()).isEqualTo("Open");
        assertThat(reply.getUser().getName()).isEqualTo("John");
        assertThat(reply.getDepartment().getDepartmentName()).isEqualTo("Sales");
    }
}