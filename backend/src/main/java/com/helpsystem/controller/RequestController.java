package com.helpsystem.controller;

import com.helpsystem.domain.Request;
import com.helpsystem.dto.GetRequest;
import com.helpsystem.dto.PostRequest;
import com.helpsystem.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/requests")
public class RequestController {

    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping("/postRequest")
    public ResponseEntity<?> post(@RequestBody PostRequest postRequest) {
        try {
            Request request = requestService.post(
                    postRequest.getTitle(), postRequest.getQuestion(), postRequest.getUser());

            return ResponseEntity.ok(new RequestSummary(request.getId(), request.getTitle(), request.getQuestion()));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get-requests")
    public ResponseEntity<List<GetRequest>> getAllRequests() {
        try {

            List<Request> allRequests = requestService.getAllRequests();
            List<GetRequest> getRequestsList = new ArrayList<>();
            for (Request request : allRequests) {
                GetRequest getRequest = new GetRequest();

                getRequest.setTitle(request.getTitle());
                getRequest.setUser(request.getUser());
                getRequest.setQuestion(request.getQuestion());
                getRequest.setStatus(request.getStatus());

                getRequestsList.add(getRequest);
            }
            return ResponseEntity.ok(getRequestsList);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private record RequestSummary(int id, String title, String text) {}
}