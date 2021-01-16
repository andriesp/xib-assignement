package com.xib.assessment.service;

import com.xib.assessment.dto.ManagerDTO;
import com.xib.assessment.exception.InternalServerException;
import com.xib.assessment.exception.NotFoundException;
import com.xib.assessment.model.Manager;
import com.xib.assessment.repository.ManagerRepository;
import com.xib.assessment.validation.CustomValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository repository;
    private final CustomValidator validator;

    @Override
    public Manager findAgentById(Long id) throws InternalServerException {
        try {
            Optional<Manager> agentOptional = repository.findById(id);
            if (agentOptional.isPresent()) return agentOptional.get();
            throw new NotFoundException("Manager Not Found");
        } catch (Exception exception) {
            String message = "Unexpected error occurred finding manager by id";
            log.error(String.format("%s %s", message, id));
            throw new InternalServerException(message);
        }

    }

    @Override
    public Long createManager(ManagerDTO managerDTO) throws InternalServerException {
        validator.validate(managerDTO);
        return null;
    }
}
