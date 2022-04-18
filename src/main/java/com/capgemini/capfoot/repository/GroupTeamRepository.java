package com.capgemini.capfoot.repository;

import com.capgemini.capfoot.entity.Groupe;
import com.capgemini.capfoot.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.capfoot.entity.GroupTeam;

@Repository
public interface GroupTeamRepository extends JpaRepository<GroupTeam, Long> {
    GroupTeam findByGroupAndTeam(Groupe groupe, Team team);
}
