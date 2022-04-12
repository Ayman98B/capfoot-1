package com.capgemini.capfoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.capfoot.entity.Groupe;

@Repository
public interface GroupRepository extends JpaRepository<Groupe, Long> {
}