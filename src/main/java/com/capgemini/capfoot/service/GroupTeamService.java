package com.capgemini.capfoot.service;

import java.util.List;

import javax.mail.MessagingException;

import com.capgemini.capfoot.dto.GroupTeamResponseDto;
import com.capgemini.capfoot.entity.GroupTeam;
import com.capgemini.capfoot.entity.Groupe;
import com.capgemini.capfoot.entity.Team;

public interface GroupTeamService {
    public void addTeamToGroup(Team team, Groupe group);
    public void addWin(Team team, Groupe group);
    public void addLoss(Team team, Groupe group);
    public void addDraw(Team team, Groupe group);
    public void qualify(Team team);
    public List<GroupTeam> launchDraw();
    GroupTeam getGroupByTeam(Team team);
    public List<Team> qualifiedTeamsToLastSixteen() throws MessagingException;
    List<GroupTeamResponseDto> getAll();
    public List<Team> qualifiedTeamsToQuarterFinals();
    public void planningQuarterFinalsMatchs() throws MessagingException;
    public void planningSemiFinalsMatchs() throws MessagingException;
    public void planningFinalsMatchs() throws MessagingException;
    public List<Team> qualifiedTeamsToSemiFinals();
    public List<Team> qualifiedTeamsToFinals();
}
