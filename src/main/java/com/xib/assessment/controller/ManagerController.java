package com.xib.assessment.controller;

import com.xib.assessment.dto.ManagerDTO;
import com.xib.assessment.exception.InternalServerException;
import com.xib.assessment.model.Agent;
import com.xib.assessment.model.Manager;
import com.xib.assessment.service.ManagerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ManagerController extends BaseController {

    private final ManagerService managerService;

    @GetMapping("manager/{id}")
    public ResponseEntity<Manager> findAgent(@PathVariable("id") Long id) throws InternalServerException {
        return okResponse(managerService.findManagerById(id));
    }

    @PostMapping(value = "manager", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Agent> createAgent(@RequestBody ManagerDTO managerDTO) throws InternalServerException {
        Manager manager = managerService.createManager(managerDTO);
        return createdResponse(manager.getId());
    }
}
