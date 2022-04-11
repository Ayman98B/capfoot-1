package com.capgemini.capfoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.capfoot.entity.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

}
