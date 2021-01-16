package com.xib.assessment.model;

import com.xib.assessment.model.keys.TeamManagementKey;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class TeamManagement implements Serializable {
    @EmbeddedId
    private TeamManagementKey id;

    @ManyToOne
    @MapsId("manager_id")
    @JoinColumn(name = "manager_id")
    private Manager manager;

    @ManyToOne
    @MapsId("manager_id")
    @JoinColumn(name = "manager_id")
    private Team team;

}
