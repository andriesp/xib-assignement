package com.xib.assessment.repository;

import com.xib.assessment.model.Agent;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends PagingAndSortingRepository<Agent, Long> {

}
