package com.xib.assessment.service;

import com.xib.assessment.dto.ManagerDTO;
import com.xib.assessment.exception.InternalServerException;
import com.xib.assessment.model.Manager;

public interface ManagerService {
    Manager findAgentById(Long id) throws InternalServerException;

    Long createManager(ManagerDTO managerDTO) throws InternalServerException;
}
