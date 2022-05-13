package com.capgemini.capfoot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.capgemini.capfoot.entity.Championship;
import com.capgemini.capfoot.entity.Groupe;

@Repository
@CrossOrigin("*")
public interface GroupRepository extends JpaRepository<Groupe, Long> {
	public List<Groupe> findByChampionship(Championship chmp);
}