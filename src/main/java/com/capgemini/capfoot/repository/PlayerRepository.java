package com.capgemini.capfoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.capfoot.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}