package com.helpsystem.service;

import com.helpsystem.domain.Department;
import com.helpsystem.domain.User;
import com.helpsystem.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    void shouldRegisterUserWithHashedPassword() {

        //Arrange
        Department department = departmentRepository.save(new Department("Engineering"));

        User registered = userService.register("Alice", "alice@example.com",
                "mypassword", "Engineering");

        //Act + assert
        assertThat(registered.getPassword()).isNotEqualTo("mypassword");
    }

}