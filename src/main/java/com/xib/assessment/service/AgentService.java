package com.xib.assessment.service;

import com.xib.assessment.dto.AgentDTO;
import com.xib.assessment.exception.InternalServerException;
import com.xib.assessment.model.Agent;

import java.util.List;

public interface AgentService {
    Agent findAgentById(Long id) throws InternalServerException;

    List<Agent> getAllAgents() throws InternalServerException;

    List<Agent> findAgentsByPaging(int page, int size) throws InternalServerException;

    Long createAgent(AgentDTO agentDTO) throws InternalServerException;
}
