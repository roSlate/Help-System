package com.helpsystem.service;

import com.helpsystem.domain.Request;
import com.helpsystem.domain.User;
import com.helpsystem.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class RequestService {

    private final RequestRepository requestRepository;

    @Autowired
    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }
    public Request post(String title, String question, User user) {

        Request request = new Request(title, question,
                user.getDepartment(),
                user,
                "Open",
                LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

        return requestRepository.save(request);
    }

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }
}
