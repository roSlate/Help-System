package com.helpsystem.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ReplyTest {

    @Test
    void shouldCreateReply() {

        //Arrange
        Reply reply = new Reply("I need help as well", "What is your problem?",
                "I'm not sure", "open", "Johnny Doe", "Sales");

        //Act + assert
        assertThat(reply.getTitle()).isEqualTo("I need help as well");
        assertThat(reply.getQuestion()).isEqualTo("What is your problem?");
        assertThat(reply.getAnswer()).isEqualTo("I'm not sure");
        assertThat(reply.getStatus()).isEqualTo("open");
        assertThat(reply.getName()).isEqualTo("Johnny Doe");
        assertThat(reply.getDepartment()).isEqualTo("Sales");
    }
}