package com.xib.assessment.controller;

import com.xib.assessment.dto.AgentDTO;
import com.xib.assessment.exception.InternalServerException;
import com.xib.assessment.model.Agent;
import com.xib.assessment.service.AgentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AgentController extends BaseController {
    private final AgentService agentService;

    @GetMapping("agent/{id}")
    public ResponseEntity<Agent> findAgent(@PathVariable("id") Long id) throws InternalServerException {
        return okResponse(agentService.findAgentById(id));
    }

    @GetMapping(value = "agents", params = {"page", "size"})
    public ResponseEntity<List<Agent>> findAllAgents(
            @RequestParam("page") int page,
            @RequestParam("size") int size) throws InternalServerException {
        return okResponse(agentService.findAgentsByPaging(page, size));
    }

    @PostMapping(value = "agent", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Agent> createAgent(@RequestBody @Valid AgentDTO agentDTO) throws InternalServerException {
        Agent agent = agentService.createAgent(agentDTO);
        return createdResponse("/agent/{id}", agent.getId(), agent);
    }

    // We assign an agent to a team not a team to an agent, therefore the api resource changes from team/id/
    @PutMapping(value = "agent/{id}/team/{teamId}", produces = "application/json")
    public ResponseEntity<Agent> assignAgentToTeam(@PathVariable("id") Long agentId, @PathVariable("teamId") Long teamId) throws InternalServerException {
        return okResponse(agentService.assignTeam(agentId, teamId));
    }

}
