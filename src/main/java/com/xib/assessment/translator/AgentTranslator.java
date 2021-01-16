package com.xib.assessment.translator;

import com.xib.assessment.dto.AgentDTO;
import com.xib.assessment.model.Agent;
import com.xib.assessment.model.Team;

import java.util.Objects;
import java.util.Optional;

public class AgentTranslator {
    private AgentTranslator() {
    }

    public static Optional<Agent> translate(AgentDTO agentDTO, Team team) {
        if (Objects.isNull(agentDTO)) return Optional.empty();

        Agent agent = new Agent();
        agent.setId(agentDTO.getId());
        agent.setIdNumber(agentDTO.getIdNumber());
        agent.setLastName(agentDTO.getLastName());
        agent.setFirstName(agentDTO.getFirstName());
        agent.setTeam(team);
        return Optional.of(agent);
    }
}
