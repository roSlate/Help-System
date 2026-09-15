package com.helpsystem.repository;

import com.helpsystem.domain.Department;
import org.springframework.boot.availability.ApplicationAvailability;

import javax.sql.DataSource;

import com.helpsystem.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldSaveAndRetrieveUser() {

        Department department = new Department("Sales");

        User user = new User("John Doe", "johndoe@email.com", "password", department);
        User saved = userRepository.save(user);

        assertThat(saved.getId()).isGreaterThan(0);

        User found = userRepository.findById(saved.getId()).orElseThrow();
        assertThat(found.getName()).isEqualTo("John Doe");
        assertThat(found.getEmail()).isEqualTo("johndoe@email.com");
        assertThat(found.getDepartment()).isEqualTo("Sales");
    }

    @Test
    void shouldFindUserByEmail() {

        Department department2 = new Department("HR");
        User user = new User("Jane Doe", "jane@doe.email.com", "123", department2);
        userRepository.save(user);

        Optional<User> found = userRepository.findByEmail("jane@doe.email.com");

        assertThat(found.isPresent());
        assertThat(found.get().getName()).isEqualTo("Jane Doe");
    }

    @Test
    void shouldReturnEmptyWhenEmailNotFound() {

        Optional<User> notFound = userRepository.findByEmail("idontexistsorry@email.com");

        assertThat(notFound).isEmpty();
    }
}