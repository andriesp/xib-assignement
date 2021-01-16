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
        try {
            Optional<Manager> managerOptional = repository.findById(id);
            if (managerOptional.isPresent()) return managerOptional.get();
            throw new NotFoundException("Manager Not Found");
        } catch (Exception exception) {
            String message = "Unexpected error occurred finding manager by id";
            log.error(String.format("%s %s", message, id));
            throw new InternalServerException(message);
        }

    }

    @Override
    public Manager createManager(ManagerDTO managerDTO) throws InternalServerException {
        try {
            validator.validate(managerDTO);
            Optional<Manager> managerOptional = repository.findByIdNumber(managerDTO.getIdNumber());
            if (managerOptional.isPresent()) throw new ConflictException("Manager already exist.");

            List<Team> teamsToAssign = teamService.findByIds(managerDTO.getTeamIds());
            Optional<Manager> optionalManager = ManagerTranslator.translate(managerDTO, teamsToAssign);
            if (optionalManager.isPresent()) return saveManager(optionalManager.get());

            throw new ValidationException("Valid manager details are required.");
        } catch (Exception exception) {
            String message = "Unexpected error occurred creating a manager";
            log.error(String.format("%s", message));
            throw new InternalServerException(message);
        }
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
}
