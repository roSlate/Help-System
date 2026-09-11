package com.helpsystem.repository;

import com.helpsystem.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class UserRepositoryTest {

//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    void shouldSaveAndRetrieveUser() {
//
//        //considering using Mockito framework if dependencies go beyond primitives
//        User user = new User("John Doe", "johndoe@email.com", "password", "Sales");
//        User saved = userRepository.save(user);
//
//        assertThat(saved.getId()).isGreaterThan(0);
//
//        User found = userRepository.findById(saved.getId()).orElseThrow();
//        assertThat(found.getName()).isEqualTo("John Doe");
//        assertThat(found.getEmail()).isEqualTo("johndoe.email.com");
//        assertThat(found.getDepartment()).isEqualTo("Sales");
//    }
}