package com.xib.assessment.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Agent extends Employee implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Team team;

    public Agent() {
        super();
    }

    public Agent(Long id, String firstName, String lastName, String idNumber, Team team) {
        this.id = id;
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setIdNumber(idNumber);
        setTeam(team);
    }
}
