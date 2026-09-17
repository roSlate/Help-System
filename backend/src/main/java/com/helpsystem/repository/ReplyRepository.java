package com.helpsystem.repository;

import com.helpsystem.domain.Reply;
import com.helpsystem.domain.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

    List<Reply> findByRequest (Request request);
}