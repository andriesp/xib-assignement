package com.xib.assessment.service;

import com.xib.assessment.exception.InternalServerException;
import com.xib.assessment.exception.NotFoundException;
import com.xib.assessment.model.Agent;
import com.xib.assessment.repository.AgentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AgentServiceImpl implements AgentService {

    private final AgentRepository repository;

    @Override
    public Agent findAgentById(Long id) throws InternalServerException {
        try {
            Optional<Agent> agentOptional = repository.findById(id);
            if (agentOptional.isPresent()) return agentOptional.get();
            throw new NotFoundException("Agent Not Found");
        } catch (Exception exception) {
            String message = "Unexpected error occurred finding agent by id";
            log.error(String.format("%s %s", message, id));
            throw new InternalServerException(message);
        }

    }

    @Override
    public List<Agent> getAllAgents() throws InternalServerException {
        try {
            return repository.findAll();
        } catch (Exception exception) {
            String message = "Unexpected error occurred finding agents";
            log.error(String.format("%s", message));
            throw new InternalServerException(message);
        }
    }
}
