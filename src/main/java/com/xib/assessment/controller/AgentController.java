package com.xib.assessment.controller;

import com.xib.assessment.dto.AgentDTO;
import com.xib.assessment.exception.InternalServerException;
import com.xib.assessment.model.Agent;
import com.xib.assessment.service.AgentService;
import com.xib.assessment.service.TeamService;
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
    private final TeamService teamService;

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
        log.info(String.valueOf(agent));
        return createdResponse(agent.getId());
    }


    @PutMapping(value = "team/{id}/agent/{agentId}", produces = "application/json")
    public ResponseEntity<Agent> assignAgentToTeam(@PathVariable("id") Long teamId, @PathVariable("agentId") Long agentId) throws InternalServerException {
        return okResponse(agentService.assignTeam(teamId, agentId));
    }

}
