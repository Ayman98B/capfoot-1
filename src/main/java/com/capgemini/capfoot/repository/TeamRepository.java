package com.capgemini.capfoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.capfoot.entity.Team;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findByName(String name);
}