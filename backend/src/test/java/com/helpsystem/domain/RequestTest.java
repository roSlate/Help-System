package com.helpsystem.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RequestTest {

    @Test
    void shouldCreateRequest() {

        //Arrange
        Department department = new Department("Sales");
        User user = new User("John Doe","johnnyboy@mail.com","password", department);

        LocalDateTime date = LocalDateTime.of(2026, 9, 8, 14, 30);
        Request request = new Request("I need help!", "Can't access my account",
                department, user,
                "open", date);

        //Act + assert
        assertThat(request.getTitle()).isEqualTo("I need help!");
        assertThat(request.getQuestion()).isEqualTo("Can't access my account");
        assertThat(request.getDepartment().getDepartmentName()).isEqualTo("Sales");
        assertThat(request.getUser().getName()).isEqualTo("John Doe");
        assertThat(request.getStatus()).isEqualTo("open");
        assertThat(request.getCreationDate()).isEqualTo(date);
    }
}