package com.helpsystem.repository;

import com.helpsystem.domain.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    void shouldSaveAndRetrieveDepartment() {

        Department department = new Department("Sales");
        Department saved = departmentRepository.save(department);

        assertThat(saved.getId()).isGreaterThan(0);

        Department found = departmentRepository.findById(saved.getId()).orElseThrow();
        assertThat(found.getDepartment()).isEqualTo("Sales");
    }
}