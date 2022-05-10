package com.capgemini.capfoot.repository;

import com.capgemini.capfoot.entity.Groupe;
import com.capgemini.capfoot.entity.Site;
import com.capgemini.capfoot.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.capfoot.entity.GroupTeam;

import java.util.List;

@Repository
public interface GroupTeamRepository extends JpaRepository<GroupTeam, Long> {
    GroupTeam findByGroupAndTeam(Groupe groupe, Team team);
    GroupTeam findByTeam(Team team);
    @Query(value = "SELECT group FROM GroupTeam group ORDER BY group_id")
    List<GroupTeam> getGroupsAndTheirTeams();
}
