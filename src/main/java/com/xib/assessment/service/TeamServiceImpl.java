package com.xib.assessment.service;

import com.xib.assessment.dto.TeamDTO;
import com.xib.assessment.exception.InternalServerException;
import com.xib.assessment.exception.NotFoundException;
import com.xib.assessment.model.Team;
import com.xib.assessment.repository.TeamRepository;
import com.xib.assessment.validation.CustomValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeamServiceImpl implements TeamService {

    private final TeamRepository repository;
    private final CustomValidator validator;

    @Override
    public Team findTeamById(Long id) throws InternalServerException {
        try {
            Optional<Team> agentOptional = repository.findById(id);
            if (agentOptional.isPresent()) return agentOptional.get();
            throw new NotFoundException("Team Not Found");
        } catch (Exception exception) {
            String message = "Unexpected error occurred finding team by id";
            log.error(String.format("%s %s details %s", message, id, exception.getMessage()));
            throw new InternalServerException(message);
        }

    }

    @Override
    public List<Team> getAllTeams() throws InternalServerException {
        try {
            return repository.findAll();
        } catch (Exception exception) {
            String message = "Unexpected error occurred finding teams";
            log.error(String.format("%s", message));
            throw new InternalServerException(message);
        }
    }

    @Override
    public Long createTeam(TeamDTO teamDTO) throws InternalServerException {
        validator.validate(teamDTO);

        return null;
    }
}
