package com.helpsystem.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void shouldCreateUser() {

        //Arrange
        User johnUser = new User(2, "John Doe", "john@doe.email.com",
                "123", "Sales");

        //Act + assert
        assertThat(johnUser.getName()).isEqualTo("John Doe");
        assertThat(johnUser.getId()).isEqualTo(2);
        assertThat(johnUser.getDepartment()).isEqualTo("Sales");
        assertThat(johnUser.getEmail().equals("john@doe.email.com"));

        //ask Murilo why it's "equals" here ^
    }
}