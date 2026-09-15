package com.helpsystem.controller;

import com.helpsystem.domain.User;
import com.helpsystem.dto.LoginRequest;
import com.helpsystem.dto.RegisterRequest;
import com.helpsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {

        try {

            User user = userService.register(
                    request.getName(), request.getEmail(), request.getPassword(), request.getDepartment());

            return ResponseEntity.ok(new UserSummary(user.getId(), user.getName(), user.getEmail()));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        Optional<User> userLogging = userService.login(request.getEmail(), request.getPassword());

        if (userLogging.isPresent()) {
            User user = userLogging.get();
            return ResponseEntity.ok(new UserSummary(user.getId(), user.getName(), user.getEmail()));
        }

        return ResponseEntity.status(401).body("Invalid email or password");
    }

    private record UserSummary(int id, String name, String email) {}
}