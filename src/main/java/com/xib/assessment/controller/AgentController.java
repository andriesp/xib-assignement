package com.xib.assessment.controller;

import com.xib.assessment.exception.InternalServerException;
import com.xib.assessment.model.Agent;
import com.xib.assessment.service.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    private <T> ResponseEntity<T> okResponse(T object) {
        return ResponseEntity.ok(object);
    }


}
