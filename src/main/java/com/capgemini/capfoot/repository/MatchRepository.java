package com.capgemini.capfoot.repository;

import com.capgemini.capfoot.entity.Championship_State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.capfoot.entity.MatchDisputee;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<MatchDisputee, Long> {
    List<MatchDisputee> findByStage(Championship_State stage);
}
