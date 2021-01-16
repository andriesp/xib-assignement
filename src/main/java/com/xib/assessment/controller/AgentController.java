package com.xib.assessment.controller;

import com.xib.assessment.dto.AgentDTO;
import com.xib.assessment.exception.InternalServerException;
import com.xib.assessment.model.Agent;
import com.xib.assessment.service.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AgentController {

    private final AgentService agentService;

    @GetMapping("agent/{id}")
    public ResponseEntity<Agent> findAgent(@PathVariable("id") Long id) throws InternalServerException {
        return okResponse(agentService.findAgentById(id));
    }

    @GetMapping("agents")
    public ResponseEntity<List<Agent>> findAllAgents() throws InternalServerException {
        return okResponse(agentService.getAllAgents());
    }

    @PostMapping(value = "agent", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Agent> createAgent(@RequestBody AgentDTO agentDTO) throws InternalServerException {
        return createdResponse(agentService.createAgent(agentDTO));
    }

    private <T> ResponseEntity<T> okResponse(T object) {
        return ResponseEntity.ok(object);
    }

    private <T> ResponseEntity<T> createdResponse(Long id) {
        return ResponseEntity.created(URI.create(String.format("agent/%s", id))).build();
    }


}
