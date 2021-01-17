package com.xib.assessment.repository;

import com.xib.assessment.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("select t from Team t where t not in (select a.team from Agent a)")
    List<Team> findUnallocatedTeamsByAgent();

    @Query(value = "select t from Team t where t not in (select tm from t.managers tm)")
    List<Team> findUnallocatedTeamsByManager();
}


