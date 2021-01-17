package com.xib.assessment.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Manager extends Employee {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany
    @JoinTable(name = "team_management",
            joinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "manager_id",
                    referencedColumnName = "id"))
    private Set<Team> teams;
}
