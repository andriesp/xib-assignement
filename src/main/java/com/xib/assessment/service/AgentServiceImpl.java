package com.xib.assessment.service;

import com.xib.assessment.dto.AgentDTO;
import com.xib.assessment.exception.InternalServerException;
import com.xib.assessment.exception.NotFoundException;
import com.xib.assessment.model.Agent;
import com.xib.assessment.model.Team;
import com.xib.assessment.repository.AgentRepository;
import com.xib.assessment.translator.AgentTranslator;
import com.xib.assessment.validation.CustomValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AgentServiceImpl implements AgentService {

    private final AgentRepository repository;
    private final CustomValidator validator;
    private final TeamService teamService;

    @Override
    public Agent findAgentById(Long id) throws InternalServerException {
        Optional<Agent> agentOptional = findById(id);
        if (agentOptional.isPresent()) return agentOptional.get();
        throw new NotFoundException("Agent Not Found");
    }

    /**
     * @return A list of agents.
     * @throws InternalServerException If any server related issues.
     * @deprecated Deprecated due to performance issues. Use {@link AgentService#findAgentsByPaging(int, int)}.
     */
    @Deprecated
    @Override
    public List<Agent> getAllAgents() throws InternalServerException {
        try {
            return (List<Agent>) repository.findAll();
        } catch (Exception exception) {
            String message = "Unexpected error occurred finding agents";
            log.error(String.format("%s", message));
            throw new InternalServerException(message);
        }
    }

    @Override
    public List<Agent> findAgentsByPaging(int page, int size) throws InternalServerException {
        try {
            Page<Agent> paging = repository.findAll(PageRequest.of(page, size));
            return paging.toList().stream()
                    .map(agent -> new Agent(agent.getId(), agent.getFirstName(), agent.getLastName(), null, agent.getTeam()))
                    .collect(Collectors.toList());
        } catch (Exception exception) {
            String message = "Unexpected error occurred finding agents";
            log.error(String.format("%s", message));
            throw new InternalServerException(message);
        }
    }

    @Override
    public Agent createAgent(AgentDTO agentDTO) throws InternalServerException {
        try {
            validator.validate(agentDTO);
            Optional<Agent> optionalAgent = AgentTranslator.translate(agentDTO, null);
            if (optionalAgent.isPresent()) {
                return saveAgent(optionalAgent.get());
            }
            throw new ValidationException("Agent is required.");
        } catch (Exception exception) {
            String message = "Unexpected error occurred finding agents";
            log.error(String.format("%s", message));
            throw new InternalServerException(message);
        }
    }

    @Override
    public Agent assignTeam(Long teamId, Long agentId) throws InternalServerException {
        Agent agent = findAgentById(agentId);
        Team team = teamService.findTeamById(teamId);
        agent.setTeam(team);
        return agent;
    }

    private Agent saveAgent(Agent agent) throws InternalServerException {
        try {
            return repository.save(agent);
        } catch (Exception exception) {
            String message = "Unexpected error occurred finding agents";
            log.error(String.format("%s", message));
            throw new InternalServerException(message);
        }
    }

    private Optional<Agent> findById(Long id) throws InternalServerException {
        try {
            return repository.findById(id);
        } catch (Exception exception) {
            String message = "Unexpected error occurred finding agent by id";
            log.error(String.format("%s %s", message, id));
            throw new InternalServerException(message);
        }
    }
}
