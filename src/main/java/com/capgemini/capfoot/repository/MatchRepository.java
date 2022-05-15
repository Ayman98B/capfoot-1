package com.capgemini.capfoot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.capfoot.entity.Championship_State;
import com.capgemini.capfoot.entity.MatchDisputee;

@Repository
public interface MatchRepository extends JpaRepository<MatchDisputee, Long> {
    List<MatchDisputee> findByStage(Championship_State stage);
}
