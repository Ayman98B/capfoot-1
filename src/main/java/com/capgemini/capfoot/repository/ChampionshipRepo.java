package com.capgemini.capfoot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.capfoot.entity.Championship;

@Repository
public interface ChampionshipRepo extends JpaRepository<Championship, Long> {

	@Query("SELECT COUNT(*) FROM Championship c where c.progress = 'TRUE'")
	public int findNbProgressTrue();

	public Championship findByLabel(String name);
}
