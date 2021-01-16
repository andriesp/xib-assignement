package com.xib.assessment.translator;

import com.xib.assessment.dto.ManagerDTO;
import com.xib.assessment.model.Manager;
import com.xib.assessment.model.Team;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ManagerTranslator {

    private ManagerTranslator() {
    }

    public static Optional<Manager> translate(ManagerDTO agentDTO, List<Team> teams) {
        if (Objects.isNull(agentDTO)) return Optional.empty();

        Manager manager = new Manager();
        manager.setId(agentDTO.getId());
        manager.setIdNumber(agentDTO.getIdNumber());
        manager.setLastName(agentDTO.getLastName());
        manager.setFirstName(agentDTO.getFirstName());
        manager.setTeams(new HashSet<>(teams));
        return Optional.of(manager);
    }
}
