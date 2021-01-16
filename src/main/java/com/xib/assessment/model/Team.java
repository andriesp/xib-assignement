package com.xib.assessment.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
public class Team implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "manager")
    private Set<TeamManagement> managers;

    @OneToMany(mappedBy = "team")
    private Set<Agent> agents;
}
