package com.xib.assessment.service;

import com.xib.assessment.dto.TeamDTO;
import com.xib.assessment.exception.InternalServerException;
import com.xib.assessment.model.Team;

import java.util.List;
import java.util.Set;

public interface TeamService {
    Team findTeamById(Long id) throws InternalServerException;

    List<Team> getAllTeams() throws InternalServerException;

    Team createTeam(TeamDTO teamDTO) throws InternalServerException;

    List<Team> findByIds(Set<Long> ids) throws InternalServerException;
}
