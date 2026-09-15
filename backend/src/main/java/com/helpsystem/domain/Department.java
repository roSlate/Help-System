package com.helpsystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Class for Department
 * Each user belongs to a departmentName
 */

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // find a way to make this protected so it's unique (i.e., if "HR" already exists, do not allow
    // for repeated creation)
    String departmentName;

    protected Department() {
    }

    public Department(String departmentName) {

        if (departmentName == null)
            throw new IllegalArgumentException("Department cannot be null");

        this.departmentName = departmentName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public int getId() {
        return id;
    }

    //Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
