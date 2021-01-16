package com.xib.assessment.model;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@MappedSuperclass
public abstract class Employee implements Serializable {
    private String firstName;
    private String lastName;
    private String idNumber;

    protected Employee() {
    }

    protected Employee(String firstName, String lastName, String idNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
    }
}
