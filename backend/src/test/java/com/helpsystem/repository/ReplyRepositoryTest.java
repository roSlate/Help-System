package com.helpsystem.repository;

import com.helpsystem.domain.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ReplyRepositoryTest {

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    void shouldSaveAndRetrieveReply() {

        Reply reply = new Reply("Help!", "How do I do this?",
                "Do it like this!", "very serious", "Jane Doe", "HR");

        Reply saved = replyRepository.save(reply);

        assertThat(saved.getId()).isGreaterThan(0);

        Reply found = replyRepository.findById(saved.getId()).orElseThrow();

        assertThat(found.getTitle()).isEqualTo("Help!");
        assertThat(found.getQuestion()).isEqualTo("How do I do this?");
        assertThat(found.getAnswer()).isEqualTo("Do it like this!");
        assertThat(found.getStatus()).isEqualTo("very serious");
        assertThat(found.getName()).isEqualTo("Jane Doe");
        assertThat(found.getDepartment()).isEqualTo("HR");
    }
}