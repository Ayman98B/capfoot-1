package com.capgemini.capfoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.capfoot.entity.Championship;

@Repository
public interface ChampionshipRepo extends JpaRepository<Championship, Long> {

}
