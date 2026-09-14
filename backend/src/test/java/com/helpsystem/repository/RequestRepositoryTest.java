package com.helpsystem.repository;

import com.helpsystem.domain.Request;
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

    @Test
    void shouldSaveAndRetrieveRequest() {

        LocalDateTime date = LocalDateTime.now();
        date = date.truncatedTo(ChronoUnit.SECONDS);
        Request request = new Request("Help!", "How do I do this?",
                "HR", "Jane Doe", "very serious", date);

        Request saved = requestRepository.save(request);

        assertThat(saved.getId()).isGreaterThan(0);

        Request found = requestRepository.findById(saved.getId()).orElseThrow();

        assertThat(found.getTitle()).isEqualTo("Help!");
        assertThat(found.getQuestion()).isEqualTo("How do I do this?");
        assertThat(found.getStatus()).isEqualTo("very serious");
        assertThat(found.getName()).isEqualTo("Jane Doe");
        assertThat(found.getDepartment()).isEqualTo("HR");
        assertThat(found.getCreationDate()).isEqualTo(date);
    }
}