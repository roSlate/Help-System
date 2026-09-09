package com.helpsystem.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RequestTest {

    @Test
    void shouldCreateRequest() {

        //Arrange
        Request request = new Request("I need help!", "Can't access my account",
                "sales", "John Doe",
                "open", LocalDateTime.of(2026, 9, 8, 14, 30));

        //Act + assert
        assertThat(request.getTitle()).isEqualTo("I need help!");
        assertThat(request.getQuestion()).isEqualTo("Can't access my account");
        assertThat(request.getDepartment()).isEqualTo("sales");
        assertThat(request.getName()).isEqualTo("John Doe");
        assertThat(request.getStatus()).isEqualTo("open");
    }
}