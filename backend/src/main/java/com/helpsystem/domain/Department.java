package com.helpsystem.domain;

import jakarta.persistence.*;

/**
 * Class for Department
 * Each user belongs to a departmentName
 */

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
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
