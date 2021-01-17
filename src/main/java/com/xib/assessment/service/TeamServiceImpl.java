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
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeamServiceImpl implements TeamService {

    private final TeamRepository repository;
    private final CustomValidator validator;

    @Override
    public Team findTeamById(Long id) throws InternalServerException {
        try {
            Optional<Team> optionalTeam = repository.findById(id);
            if (optionalTeam.isPresent()) return optionalTeam.get();
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
    public Team createTeam(TeamDTO teamDTO) throws InternalServerException {
        validator.validate(teamDTO);
        return saveTeam(new Team(teamDTO.getName()));
    }

    @Override
    public List<Team> findByIds(Set<Long> ids) throws InternalServerException {
        try {
            return repository.findAllById(ids);
        } catch (Exception exception) {
            String message = "Unexpected error occurred finding teams by ids";
            log.error(String.format("%s details %s", message, exception.getMessage()));
            throw new InternalServerException(message);
        }
    }

    private Team saveTeam(Team team) throws InternalServerException {
        try {
            return repository.save(team);
        } catch (Exception exception) {
            String message = "Unexpected error occurred saving team";
            log.error(String.format("%s", message));
            throw new InternalServerException(message);
        }
    }
}
