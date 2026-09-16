package com.helpsystem.repository;

import com.helpsystem.domain.Department;
import com.helpsystem.domain.Reply;
import com.helpsystem.domain.Request;
import com.helpsystem.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ReplyRepositoryTest {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RequestRepository requestRepository;

    @Test
    void shouldSaveAndRetrieveReply() {

        //Arrange
        Department department = departmentRepository.save(new Department("HR"));

        User user = userRepository.save(new User("Jane Doe","JaneDoe@mail.com","password", department));
        Request request = requestRepository.save(new Request("Help!", "How do I do this?", department, user,
                "very serious", LocalDateTime.now()));
        Reply reply = new Reply("Do it like this!", request, user, department);

        Reply saved = replyRepository.save(reply);

        //Act + assert

        assertThat(saved.getId()).isGreaterThan(0);

        Reply found = replyRepository.findById(saved.getId()).orElseThrow();

        assertThat(found.getRequest().getTitle()).isEqualTo("Help!");
        assertThat(found.getRequest().getQuestion()).isEqualTo("How do I do this?");
        assertThat(found.getAnswer()).isEqualTo("Do it like this!");
        assertThat(found.getRequest().getStatus()).isEqualTo("very serious");
        assertThat(found.getUser().getName()).isEqualTo("Jane Doe");
        assertThat(found.getDepartment().getDepartmentName()).isEqualTo("HR");
    }
}