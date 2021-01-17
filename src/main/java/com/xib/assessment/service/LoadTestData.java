package com.xib.assessment.service;

import com.xib.assessment.model.Agent;
import com.xib.assessment.model.Manager;
import com.xib.assessment.model.Team;
import com.xib.assessment.repository.AgentRepository;
import com.xib.assessment.repository.ManagerRepository;
import com.xib.assessment.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LoadTestData {
    private final AgentRepository agentRepository;
    private final TeamRepository teamRepository;
    private final ManagerRepository managerRepository;

    @PostConstruct
    @Transactional
    public void execute() {
        Team team1 = createTeam("Marvel");
        Team team2 = createTeam("DC");
        createTeam("Blue");

        createAgent("Bruce", "Banner", "1011125190081", team1);
        createAgent("Tony", "Stark", "6912115191083", team1);
        createAgent("Peter", "Parker", "7801115190084", team1);
        createAgent("Bruce", "Wayne", "6511185190085", team2);
        createAgent("Clark", "Kent", "5905115190086", team2);

        createManager("Joe", "Doe", "10245645656", Collections.singletonList(team1));
        createManager("Dan", "Ten", "123456456", Collections.singletonList(team1));
    }

    private Team createTeam(String name) {
        Team t = new Team();
        t.setName(name);
        return teamRepository.save(t);
    }

    private Agent createAgent(String firstName, String lastName, String idNumber, Team team) {
        Agent a = new Agent();
        a.setFirstName(firstName);
        a.setLastName(lastName);
        a.setIdNumber(idNumber);
        a.setTeam(team);
        return agentRepository.save(a);
    }

    private Manager createManager(String firstName, String lastName, String idNumber, List<Team> team) {
        Manager manager = new Manager();
        manager.setFirstName(firstName);
        manager.setLastName(lastName);
        manager.setIdNumber(idNumber);
        manager.setTeams(new HashSet<>(team));
        return managerRepository.save(manager);
    }
}
