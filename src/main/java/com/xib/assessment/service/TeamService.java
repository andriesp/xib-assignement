package com.xib.assessment.service;

import com.xib.assessment.dto.TeamDTO;
import com.xib.assessment.exception.InternalServerException;
import com.xib.assessment.model.Agent;
import com.xib.assessment.model.Team;

import java.util.List;

public interface TeamService {
    Team findTeamById(Long id) throws InternalServerException;

    List<Team> getAllTeams() throws InternalServerException;

    Long createTeam(TeamDTO agentDTO) throws InternalServerException;
}
