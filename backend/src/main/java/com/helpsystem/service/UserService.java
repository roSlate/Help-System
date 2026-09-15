package com.helpsystem.service;

import com.helpsystem.domain.Department;
import com.helpsystem.domain.User;
import com.helpsystem.repository.DepartmentRepository;
import com.helpsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public UserService(UserRepository userRepository, DepartmentRepository departmentRepository) {

        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User register(String name, String email, String rawPassword, String departmentName) {

        if (userRepository.findByEmail(email).isPresent())
            throw new IllegalArgumentException("Email already registered");

        Department department = departmentRepository.findByDepartmentName(departmentName)
                .orElseThrow(() -> new IllegalArgumentException("Department not found: " + departmentName));

        String hashedPassword = passwordEncoder.encode(rawPassword);
        User user = new User(name, email, hashedPassword, department);

        return userRepository.save(user);
    }

    public Optional<User> login(String email, String rawPassword) {

        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent() && passwordEncoder.matches(rawPassword, user.get().getPassword()))
            return user;

        return Optional.empty();
    }
}
