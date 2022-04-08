package com.capgemini.capfoot.repository;

import com.capgemini.capfoot.entity.Groupe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupeRepository extends JpaRepository<Groupe,Long> {
}