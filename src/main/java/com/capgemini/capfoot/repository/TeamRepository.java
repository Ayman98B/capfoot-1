package com.capgemini.capfoot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.capfoot.entity.Championship_State;
import com.capgemini.capfoot.entity.Site;
import com.capgemini.capfoot.entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findByName(String name);
    List<Team> findTeamsBySite(Site site);
    List<Team> findByStage(Championship_State stage);
}