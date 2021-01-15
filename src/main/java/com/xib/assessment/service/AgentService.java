package com.xib.assessment.service;

import com.xib.assessment.exception.InternalServerException;
import com.xib.assessment.model.Agent;

public interface AgentService {
    Agent findAgentById(Long id) throws InternalServerException;
}
