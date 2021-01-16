package com.xib.assessment.repository;

import com.xib.assessment.model.Manager;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends PagingAndSortingRepository<Manager, Long> {

}
