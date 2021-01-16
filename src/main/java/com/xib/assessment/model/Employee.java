package com.xib.assessment.model;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class Employee implements Serializable {
    private String firstName;
    private String lastName;
    private String idNumber;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String idNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
    }
}
