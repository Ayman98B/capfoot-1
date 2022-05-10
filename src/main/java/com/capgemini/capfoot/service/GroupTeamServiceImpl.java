package com.capgemini.capfoot.service;

import com.capgemini.capfoot.entity.*;
import com.capgemini.capfoot.repository.GroupRepository;
import com.capgemini.capfoot.repository.GroupTeamRepository;
import com.capgemini.capfoot.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GroupTeamServiceImpl implements  GroupTeamService{

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    GroupTeamRepository groupTeamRepository;

    @Autowired
    MatchService matchService;

    @Override
    public void addTeamToGroup(Team team, Groupe group) {
        Team findTeam = teamRepository.findById(team.getId()).get();
        Groupe findGroup = groupRepository.findById(group.getId()).get();
        if(findTeam != null && findGroup != null){
            GroupTeam groupTeam1 = new GroupTeam();
            groupTeam1.setGroup(group);
            groupTeam1.setTeam(team);
            groupTeamRepository.save(groupTeam1);
        }

    }

    @Override
    public void addWin(Team team, Groupe group) {
        GroupTeam groupTeam1 = groupTeamRepository.findByGroupAndTeam(group,team);
        if(groupTeam1!= null){
            groupTeam1.setNbWonMatch((groupTeam1.getNbWonMatch())+1);
            groupTeam1.setCumulPoint((groupTeam1.getCumulPoint())+3);
            groupTeamRepository.save(groupTeam1);
        }
    }

    @Override
    public void addLoss(Team team, Groupe group) {
        GroupTeam groupTeam1 = groupTeamRepository.findByGroupAndTeam(group,team);
        if(groupTeam1!= null){
            groupTeam1.setNbLossMatch((groupTeam1.getNbLossMatch())+1);
            groupTeamRepository.save(groupTeam1);
        }
    }

    @Override
    public void addDraw(Team team, Groupe group) {
        GroupTeam groupTeam1 = groupTeamRepository.findByGroupAndTeam(group,team);
        if(groupTeam1!=null){
            groupTeam1.setNbDrawMatch((groupTeam1.getNbDrawMatch())+1);
            groupTeam1.setCumulPoint((groupTeam1.getCumulPoint())+1);
            groupTeamRepository.save(groupTeam1);
        }
    }

    @Override
    public List<GroupTeam> launchDraw() {
        List<Team> casaTeams = teamRepository.findTeamsBySite(Site.CASABLANCA);
        List<Team> rabatTeams = teamRepository.findTeamsBySite(Site.RABAT);

        List<Groupe> groups = groupRepository.findAll();
        handleDrawForTeamsPerSite(casaTeams, groups.subList(0, 4));
        handleDrawForTeamsPerSite(rabatTeams, groups.subList(4, 8));
        return groupTeamRepository.findAll();
    }

    private void handleDrawForTeamsPerSite(List<Team> teams, List<Groupe> groupes) {
        int index = 0;
        int length;
        Collections.shuffle(teams);
        List<Team> restOfteamsToShuffle = teams;
        while (index < teams.size()) {
            if (index > 0) {
                length = restOfteamsToShuffle.size();
                restOfteamsToShuffle = restOfteamsToShuffle.subList(4, length);
            }
            List<Team> teamsTofillGroupWith = restOfteamsToShuffle.subList(0, 4);
            Groupe nextGroupe = groupes.get(index / 4);
            index = fillGroupWithTeamsRandomly(teamsTofillGroupWith, nextGroupe, index);
        }
    }

    private int fillGroupWithTeamsRandomly(List<Team> fourTeamsGroup, Groupe groupe, int index) {
        List<GroupTeam> groupTeams = new ArrayList<>();
        for (int j = 0; j < fourTeamsGroup.size(); j++) {
            GroupTeam groupTeam1 = new GroupTeam();
            groupTeam1.setGroup(groupe);
            Team team = fourTeamsGroup.get(j);
            groupTeam1.setTeam(team);
            groupTeams.add(groupTeam1);
            addTeamToGroup(team, groupe);
        }
        planifierMatchGroupe(fourTeamsGroup, groupe);
        index += 4;
        return index;
    }

    private void planifierMatchGroupe(List<Team> fourTeamsGroup, Groupe groupe) {
        for (int i = 0; i < fourTeamsGroup.size() - 1; i++) {
            Team team_away = fourTeamsGroup.get(i);
            for (int j = i+1; j < fourTeamsGroup.size(); j++) {
                Team team_home = fourTeamsGroup.get(j);
                MatchDisputee matchDispute =
                        new MatchDisputee(true, false, fourTeamsGroup.get(i).getSite(), team_home, team_away);
                matchService.addMatch(matchDispute);
            }
        }
    }
}
