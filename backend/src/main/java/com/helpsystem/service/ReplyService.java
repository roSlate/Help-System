package com.helpsystem.service;

import com.helpsystem.domain.Reply;
import com.helpsystem.domain.Request;
import com.helpsystem.domain.User;
import com.helpsystem.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ReplyService {
    private final ReplyRepository replyRepository;

    @Autowired
    public ReplyService(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }
    public Reply post(String answer, Request request, User user) {

        Reply reply = new Reply(answer,
                request,
                user,
                user.getDepartment());

        return replyRepository.save(reply);
    }

    public List<Reply> getAllRepliesToRequest(Request request) {
        return replyRepository.findByRequest(request);
    }
}
