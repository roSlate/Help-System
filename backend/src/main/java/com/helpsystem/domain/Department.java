package com.helpsystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Class for Department
 * Each user belongs to a department
 */

@Entity
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

    public String getDepartmentName() {
        return department;
    }

    public int getId() {
        return id;
    }

    //Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
