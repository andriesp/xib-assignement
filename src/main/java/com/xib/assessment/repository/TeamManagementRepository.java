package com.xib.assessment.repository;

import com.xib.assessment.model.TeamManagement;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamManagementRepository extends PagingAndSortingRepository<TeamManagement, Long> {

}
