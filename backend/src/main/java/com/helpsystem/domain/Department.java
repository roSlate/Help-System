package com.helpsystem.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Class for Department
 * Each user belongs to a department
 */

public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    String department;

    protected Department() {
    }

    public Department(String department) {

        if (department == null)
            throw new IllegalArgumentException("Department cannot be null");

        this.department = department;
    }

    public String getDepartment() {
        return department;
    }
}
