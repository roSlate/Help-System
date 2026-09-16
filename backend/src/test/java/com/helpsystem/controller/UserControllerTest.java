package com.helpsystem.controller;

import com.helpsystem.domain.Department;
import com.helpsystem.dto.LoginRequest;
import com.helpsystem.dto.RegisterRequest;
import com.helpsystem.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldRegisterUserSuccessfully() throws Exception {

        departmentRepository.save(new Department("Marketing"));

        RegisterRequest registerRequest = new RegisterRequest();

        registerRequest.setName("Frank From Marketing");
        registerRequest.setEmail("frank@example.com");
        registerRequest.setPassword("password1");
        registerRequest.setDepartment("Marketing");

        mockMvc.perform(post("/users/register").contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerRequest))).andExpect(status().isOk());
    }

    @Test
    void shouldLoginSuccessfully() throws Exception {

        departmentRepository.save(new Department("Finance"));

        RegisterRequest registerRequest2 = new RegisterRequest();

        registerRequest2.setName("Hank From Finance");
        registerRequest2.setEmail("hank@email.com");
        registerRequest2.setPassword("password2");
        registerRequest2.setDepartment("Finance");

        mockMvc.perform(post("/users/registerRequest2").contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerRequest2)));


        LoginRequest loginRequest = new LoginRequest();

        loginRequest.setEmail("hank@email.com");
        loginRequest.setPassword("password2");
    }
}