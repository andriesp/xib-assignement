package com.xib.assessment.service;

import com.xib.assessment.dto.ManagerDTO;
import com.xib.assessment.exception.ConflictException;
import com.xib.assessment.exception.InternalServerException;
import com.xib.assessment.exception.NotFoundException;
import com.xib.assessment.model.Manager;
import com.xib.assessment.model.Team;
import com.xib.assessment.repository.ManagerRepository;
import com.xib.assessment.translator.ManagerTranslator;
import com.xib.assessment.validation.CustomValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository repository;
    private final CustomValidator validator;
    private final TeamService teamService;


    @Override
    public Manager findManagerById(Long id) throws InternalServerException {
        Optional<Manager> agentOptional = findById(id);
        if (agentOptional.isPresent()) {
            return transformManager(agentOptional.get());
        }
        throw new NotFoundException("Manager Not Found");
    }

    @Override
    public Manager createManager(ManagerDTO managerDTO) throws InternalServerException {
        validator.validate(managerDTO);
        Optional<Manager> managerOptional = repository.findByIdNumber(managerDTO.getIdNumber());
        if (managerOptional.isPresent()) throw new ConflictException("Manager Already Exist.");

        List<Team> teamsToAssign = teamService.findByIds(managerDTO.getTeamIds());
        Optional<Manager> optionalManager = ManagerTranslator.translate(managerDTO, teamsToAssign);
        if (optionalManager.isPresent()) {
            Manager manager = saveManager(optionalManager.get());
            return transformManager(manager);
        }

        throw new ValidationException("Valid manager details are required.");
    }

    @Override
    public Manager assignTeam(Long managerId, Long teamId) throws InternalServerException {
        Manager manager = findManagerById(managerId);
        if (manager.getTeams().size() > 1) throw new ConflictException("Manager already assigned 2 teams");

        Team team = teamService.findTeamById(teamId);
        manager.getTeams().add(team);

        team.getManagers().add(manager);
        teamService.updateTeam(team);
        return transformManager(manager);
    }

    private Manager transformManager(Manager manager) {
        manager.getTeams().forEach(t -> t.setManagers(null));
        return manager;
    }

    private Manager saveManager(Manager manager) throws InternalServerException {
        try {
            return repository.save(manager);
        } catch (Exception exception) {
            String message = "Unexpected error occurred saving manager";
            log.error(String.format("%s details %s", message, exception.getMessage()));
            throw new InternalServerException(message);
        }
    }

    private Optional<Manager> findById(Long id) throws InternalServerException {
        try {
            return repository.findById(id);
        } catch (Exception exception) {
            String message = "Unexpected error occurred finding manager by id";
            log.error(String.format("%s %s details %s", message, id, exception.getMessage()));
            throw new InternalServerException(message);
        }
    }


}
