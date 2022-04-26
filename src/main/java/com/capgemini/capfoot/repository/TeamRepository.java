package com.capgemini.capfoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.capfoot.entity.Team;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findByName(String name);
    List<Team> findTeamsBySite(String site);
}