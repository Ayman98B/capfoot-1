package com.capgemini.capfoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.capfoot.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
}