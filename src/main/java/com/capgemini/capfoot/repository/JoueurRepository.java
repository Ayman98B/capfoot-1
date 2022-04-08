package com.capgemini.capfoot.repository;

import com.capgemini.capfoot.entity.Joueur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JoueurRepository extends JpaRepository<Joueur, Long> {
}