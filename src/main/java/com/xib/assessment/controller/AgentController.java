package com.xib.assessment.controller;

import com.xib.assessment.dto.AgentDTO;
import com.xib.assessment.exception.InternalServerException;
import com.xib.assessment.model.Agent;
import com.xib.assessment.service.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
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
    public ResponseEntity<Agent> createAgent(@RequestBody AgentDTO agentDTO) throws InternalServerException {
        return createdResponse(agentService.createAgent(agentDTO));
    }

}
