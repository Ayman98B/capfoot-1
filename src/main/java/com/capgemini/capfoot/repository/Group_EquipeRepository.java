package com.capgemini.capfoot.repository;

import com.capgemini.capfoot.entity.GroupeEquipeId;
import com.capgemini.capfoot.entity.Groupe_Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Group_EquipeRepository extends JpaRepository<Groupe_Equipe, GroupeEquipeId> {
}
