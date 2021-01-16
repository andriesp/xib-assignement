package com.xib.assessment.model.keys;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class TeamManagementKey implements Serializable {
    @Column(name = "team_id")
    private Long teamId;
    @Column(name = "manager_id")
    private Long managerId;
}
