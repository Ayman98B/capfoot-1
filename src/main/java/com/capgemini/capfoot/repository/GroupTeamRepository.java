package com.capgemini.capfoot.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.capfoot.entity.GroupTeam;
import com.capgemini.capfoot.entity.Groupe;
import com.capgemini.capfoot.entity.Team;

@Repository
public interface GroupTeamRepository extends JpaRepository<GroupTeam, Long> {
    GroupTeam findByGroupAndTeam(Groupe groupe, Team team);
    @Query(value = "SELECT group FROM GroupTeam group ORDER BY group_id")
    List<GroupTeam> getGroupsAndTheirTeams();
    GroupTeam findByTeam(Team team);
    List<GroupTeam> findByOrderByCumulPointDesc(Sort group);
    List<GroupTeam> findByOrderByGroupAsc(Sort points);

}
