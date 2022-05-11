package com.capgemini.capfoot.service;

import com.capgemini.capfoot.dto.GroupTeamResponseDto;
import com.capgemini.capfoot.entity.*;
import com.capgemini.capfoot.repository.GroupRepository;
import com.capgemini.capfoot.repository.GroupTeamRepository;
import com.capgemini.capfoot.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class GroupTeamServiceImpl implements  GroupTeamService{

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    GroupTeamRepository groupTeamRepository;

    @Autowired
    ChampionshipService championshipService;

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

    @Override
    public GroupTeam getGroupByTeam(Team team) {
        return groupTeamRepository.findByTeam(team);
    }

    @Override
    public List<GroupTeamResponseDto> getAll() {
            List<GroupTeamResponseDto> groupTeamResponseList = new ArrayList<>();
            groupTeamRepository.getGroupsAndTheirTeams().forEach(groupTeam -> {
                groupTeamResponseList.add(GroupTeamResponseDto.createGroupTeamResponseDto(groupTeam));
            });
        return groupTeamResponseList;
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
    @Override
     public List<Team> qualifiedTeams() {
        List<MatchDisputee> allMatchs = matchService.getAllMatchs();
        AtomicBoolean endGroupPhase = new AtomicBoolean(false);
        AtomicInteger GroupPhase = new AtomicInteger(0);

        for (MatchDisputee match : allMatchs) {
            if (match.getMatchState() != State.END) {
                GroupPhase.set(1);
                break;
            } else {
                endGroupPhase.set(true);
            }
        }

        System.out.println("" + GroupPhase.get());
        List<GroupTeam> teams = new ArrayList<>();
        List<Team> newTeams = new ArrayList<>();
        if (GroupPhase.get() == 1) {
            System.out.println("Not Yet Over");
        } else {
            //  teams = groupTeamRepository.findByOrderByCumulPointDesc(Sort.by("group").ascending());
            teams = groupTeamRepository.findByOrderByGroupAsc(Sort.by("cumulPoint").descending());

            for (int i = 0; i < teams.size(); i = i + 4) {
                System.out.println(teams.get(i).getTeam());
                newTeams.add(teams.get(i).getTeam());
                newTeams.add(teams.get(i+1).getTeam());
            }

            teams.stream()
                    .filter(g -> g.getGroup().getChampionship().getId() != null)
                    .findFirst()
                    .ifPresent(g -> {
                        Championship ch = g.getGroup().getChampionship();
                        ch.setStatut(Statut.QUART_FINAL);
                        championshipService.updateChampionship(ch);
                    });
        }
        planifierMatchQuatreFinal(newTeams);
        return newTeams;
    }

    private void planifierMatchQuatreFinal(List<Team> quartsFinaleTeams) {
        for (int i = 0; i < quartsFinaleTeams.size(); i= i+4) {
            Team team_away = quartsFinaleTeams.get(i);
            Team team_home = quartsFinaleTeams.get(i+3);
            Team team_away1 = quartsFinaleTeams.get(i+1);
            Team team_home1 = quartsFinaleTeams.get(i+2);
                MatchDisputee matchDispute =
                        new MatchDisputee(false, true, quartsFinaleTeams.get(i).getSite(), team_home, team_away);
            MatchDisputee matchDispute1 =
                    new MatchDisputee(false, true, quartsFinaleTeams.get(i).getSite(), team_home1, team_away1);
                matchService.addMatch(matchDispute);
                matchService.addMatch(matchDispute1);

        }
    }

}

