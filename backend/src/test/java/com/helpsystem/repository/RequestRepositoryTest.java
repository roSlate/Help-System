package com.helpsystem.repository;

import com.helpsystem.domain.Department;
import com.helpsystem.domain.Request;
import com.helpsystem.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class RequestRepositoryTest {
    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldSaveAndRetrieveRequest() {

        //Arrange
        Department department = departmentRepository.save(new Department("HR"));
        User user = userRepository.save(new User("Jane Doe","janed@mail.com","password", department));

        LocalDateTime date = LocalDateTime.now();
        date = date.truncatedTo(ChronoUnit.SECONDS);
        Request request = new Request("Help!", "How do I do this?",
                department, user, "very serious", date);

        //Act + assert

        Request saved = requestRepository.save(request);

        assertThat(saved.getId()).isGreaterThan(0);

        Request found = requestRepository.findById(saved.getId()).orElseThrow();

        assertThat(found.getTitle()).isEqualTo("Help!");
        assertThat(found.getQuestion()).isEqualTo("How do I do this?");
        assertThat(found.getStatus()).isEqualTo("very serious");
        assertThat(found.getUser().getName()).isEqualTo("Jane Doe");
        assertThat(found.getDepartment().getDepartmentName()).isEqualTo("HR");
        assertThat(found.getCreationDate()).isEqualTo(date);
    }
}