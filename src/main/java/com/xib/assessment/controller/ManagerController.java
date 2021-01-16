package com.xib.assessment.controller;

import com.xib.assessment.dto.ManagerDTO;
import com.xib.assessment.exception.InternalServerException;
import com.xib.assessment.model.Agent;
import com.xib.assessment.model.Manager;
import com.xib.assessment.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ManagerController extends BaseController {

    private final ManagerService managerService;

    @GetMapping("manager/{id}")
    public ResponseEntity<Manager> findAgent(@PathVariable("id") Long id) throws InternalServerException {
        return okResponse(managerService.findAgentById(id));
    }

    @PostMapping(value = "manager", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Agent> createAgent(@RequestBody ManagerDTO managerDTO) throws InternalServerException {
        return createdResponse(managerService.createManager(managerDTO));
    }
}
