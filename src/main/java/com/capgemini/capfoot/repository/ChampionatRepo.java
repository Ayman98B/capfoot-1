package com.capgemini.capfoot.repository;


import com.capgemini.capfoot.entity.Championat;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChampionatRepo extends JpaRepository<Championat, Long>{

}
