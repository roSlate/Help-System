package com.helpsystem.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UserTest {

    @Test
    void shouldCreateUser() {

        //Arrange
        User johnUser = new User("John Doe", "john@doe.email.com",
                "123", "Sales");

        //Act + assert
        assertThat(johnUser.getName()).isEqualTo("John Doe");
        assertThat(johnUser.getDepartment()).isEqualTo("Sales");
        assertThat(johnUser.getEmail()).isEqualTo("john@doe.email.com");
    }
}