package com.capgemini.capfoot.repository;

import com.capgemini.capfoot.entity.Equipe;
import com.capgemini.capfoot.entity.Groupe_Equipe;
import com.capgemini.capfoot.entity.Joueur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChampionatRepo extends JpaRepository<Groupe_Equipe.Championat, Long>{

    @Repository
    interface JoueurRepository extends JpaRepository<Joueur, Long> {

    }

    @Repository
    interface EquipeRepository extends JpaRepository<Equipe, Long> {
    }
}
