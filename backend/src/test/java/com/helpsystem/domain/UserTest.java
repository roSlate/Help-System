package com.helpsystem.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UserTest {

    @Test
    void shouldCreateUser() {

        //Arrange
        Department department = new Department("Sales");
        User johnUser = new User("John Doe", "john@doe.email.com",
                "123", department);

        //Act + assert
        assertThat(johnUser.getName()).isEqualTo("John Doe");
        assertThat(johnUser.getDepartment().getDepartmentName()).isEqualTo("Sales");
        assertThat(johnUser.getEmail()).isEqualTo("john@doe.email.com");
    }
}