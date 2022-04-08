package com.capgemini.capfoot.repository;

import com.capgemini.capfoot.entity.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipeRepository extends JpaRepository<Equipe, Long> {
}