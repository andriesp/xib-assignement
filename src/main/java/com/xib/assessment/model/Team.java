package com.xib.assessment.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Team implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "teams")
    private List<Manager> managers;

    public Team() {
    }

    public Team(String name) {
        this.name = name;
    }

    public Team(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
