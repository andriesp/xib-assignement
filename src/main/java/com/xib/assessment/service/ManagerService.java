package com.xib.assessment.service;

import com.xib.assessment.dto.ManagerDTO;
import com.xib.assessment.exception.InternalServerException;
import com.xib.assessment.model.Manager;

public interface ManagerService {
    Manager findManagerById(Long id) throws InternalServerException;

    Manager createManager(ManagerDTO managerDTO) throws InternalServerException;

    Manager assignTeam(Long managerId, Long teamId) throws InternalServerException;
}
