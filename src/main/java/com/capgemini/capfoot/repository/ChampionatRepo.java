package com.capgemini.capfoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.capfoot.model.Championat;

@Repository
public interface ChampionatRepo extends JpaRepository<Championat, Long>{

}
