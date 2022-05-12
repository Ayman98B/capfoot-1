package com.capgemini.capfoot.service;

import com.capgemini.capfoot.dto.GroupTeamResponseDto;
import com.capgemini.capfoot.entity.GroupTeam;
import com.capgemini.capfoot.entity.Groupe;
import com.capgemini.capfoot.entity.Team;

import java.util.List;

public interface GroupTeamService {
    public void addTeamToGroup(Team team, Groupe group);
    public void addWin(Team team, Groupe group);
    public void addLoss(Team team, Groupe group);
    public void addDraw(Team team, Groupe group);
    public void qualify(Team team);
    public List<GroupTeam> launchDraw();
    GroupTeam getGroupByTeam(Team team);
    public List<Team> qualifiedTeams();
    List<GroupTeamResponseDto> getAll();
    public List<Team> lastSexteenTeams();
}
