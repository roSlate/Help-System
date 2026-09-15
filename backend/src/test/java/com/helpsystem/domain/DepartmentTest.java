package com.helpsystem.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DepartmentTest {

    @Test
    void shouldCreateDepartment() {

        //Arrange
        Department department = new Department("sales");

        //Act + assert
        assertThat(department.getDepartmentName()).isEqualTo("sales");
    }
}