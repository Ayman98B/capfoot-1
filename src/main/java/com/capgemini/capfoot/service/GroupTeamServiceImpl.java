package com.capgemini.capfoot.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.capgemini.capfoot.dto.GroupTeamResponseDto;
import com.capgemini.capfoot.entity.Championship;
import com.capgemini.capfoot.entity.Championship_State;
import com.capgemini.capfoot.entity.GroupTeam;
import com.capgemini.capfoot.entity.Groupe;
import com.capgemini.capfoot.entity.MatchDisputee;
import com.capgemini.capfoot.entity.Match_State;
import com.capgemini.capfoot.entity.Site;
import com.capgemini.capfoot.entity.Team;
import com.capgemini.capfoot.repository.GroupRepository;
import com.capgemini.capfoot.repository.GroupTeamRepository;
import com.capgemini.capfoot.repository.TeamRepository;

@Service
public class GroupTeamServiceImpl implements  GroupTeamService {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    TeamService teamService;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    GroupTeamRepository groupTeamRepository;

    @Autowired
    ChampionshipService championshipService;

    @Autowired
    MatchService matchService;
    
    @Autowired
    EmailService emailService;

    @Override
    public void addTeamToGroup(Team team, Groupe group) {
        Team findTeam = teamRepository.findById(team.getId()).get();
        Groupe findGroup = groupRepository.findById(group.getId()).get();
        if (findTeam != null && findGroup != null) {
            GroupTeam groupTeam1 = new GroupTeam();
            groupTeam1.setGroup(group);
            groupTeam1.setTeam(team);
            groupTeamRepository.save(groupTeam1);
        }


    }

    @Override
    public void addWin(Team team, Groupe group) {
        GroupTeam groupTeam1 = groupTeamRepository.findByGroupAndTeam(group, team);
        if (groupTeam1 != null) {
            groupTeam1.setNbWonMatch((groupTeam1.getNbWonMatch()) + 1);
            groupTeam1.setCumulPoint((groupTeam1.getCumulPoint()) + 3);
            groupTeamRepository.save(groupTeam1);
        }
    }

    @Override
    public void addLoss(Team team, Groupe group) {
        GroupTeam groupTeam1 = groupTeamRepository.findByGroupAndTeam(group, team);
        if (groupTeam1 != null) {
            groupTeam1.setNbLossMatch((groupTeam1.getNbLossMatch()) + 1);
            groupTeamRepository.save(groupTeam1);
        }
    }

    @Override
    public void addDraw(Team team, Groupe group) {
        GroupTeam groupTeam1 = groupTeamRepository.findByGroupAndTeam(group, team);
        if (groupTeam1 != null) {
            groupTeam1.setNbDrawMatch((groupTeam1.getNbDrawMatch()) + 1);
            groupTeam1.setCumulPoint((groupTeam1.getCumulPoint()) + 1);
            groupTeamRepository.save(groupTeam1);
        }
    }

    @Override
    public void qualify(Team team) {

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
            for (int j = i + 1; j < fourTeamsGroup.size(); j++) {
                Team team_home = fourTeamsGroup.get(j);
                MatchDisputee matchDispute =
                        new MatchDisputee(Championship_State.GROUPE, fourTeamsGroup.get(i).getSite(), team_home, team_away);
                matchService.addMatch(matchDispute);
            }
        }
    }

    @Override
    public List<Team> qualifiedTeamsToLastSixteen() throws MessagingException {
        List<MatchDisputee> allMatchs = matchService.getAllMatchs();
        AtomicInteger GroupPhase = new AtomicInteger(0);

        for (MatchDisputee match : allMatchs) {
            if (match.getMatchState() !=Match_State.END) {
                GroupPhase.set(1);
                break;
            }
        }

        System.out.println("" + GroupPhase.get());
        List<GroupTeam> teams = new ArrayList<>();
        List<Team> newTeams = new ArrayList<>();
        if (GroupPhase.get() == 1) {
            System.out.println("Not Yet Over");
        } else {
            teams = groupTeamRepository.findByOrderByGroupAsc(Sort.by("cumulPoint").descending());

            for (int i = 0; i < teams.size(); i = i + 4) {
                newTeams.add(teams.get(i).getTeam());
                newTeams.add(teams.get(i + 1).getTeam());
            }

            teams.stream()
                    .filter(g -> g.getGroup().getChampionship().getId() != null)
                    .findFirst()
                    .ifPresent(g -> {
                        Championship ch = g.getGroup().getChampionship();
                        ch.setStatut(Championship_State.QUART_FINAL);
                        try {
							championshipService.updateChampionship(ch);
						} catch (MessagingException e) {
							e.printStackTrace();
						}
                    });
        }

        newTeams.stream().filter(team -> team.getStage().equals(Championship_State.GROUPE)).forEach(team -> {
            team.setStage(Championship_State.LAST_SIXTEEN);
            teamService.updateTeam(team);
        });

        newTeams.forEach( team -> System.out.println("Teamss"+ team.toString()));
        planifierMatchQuatreFinal(newTeams);
        emailService.sendEmailQualifiedTeams(newTeams, Championship_State.LAST_SIXTEEN);
        return newTeams;
    }

    private void planifierMatchQuatreFinal(List<Team> quartsFinaleTeams) {
        for (int i = 0; i < quartsFinaleTeams.size(); i = i + 4) {
            Team team_away = quartsFinaleTeams.get(i);
            Team team_home = quartsFinaleTeams.get(i + 3);
            Team team_away1 = quartsFinaleTeams.get(i + 1);
            Team team_home1 = quartsFinaleTeams.get(i + 2);

            MatchDisputee matchDispute =
                    new MatchDisputee(Championship_State.LAST_SIXTEEN, quartsFinaleTeams.get(i).getSite(), team_home, team_away);
            MatchDisputee matchDispute1 =
                    new MatchDisputee(Championship_State.LAST_SIXTEEN, quartsFinaleTeams.get(i).getSite(), team_home1, team_away1);
            matchService.addMatch(matchDispute);
            matchService.addMatch(matchDispute1);
        }
    }
    public List<Team> qualifiedTeamsToQuarterFinals(){
        List<Team> quarterFinalsTeams =  teamService.getAllTeamsByStage(Championship_State.QUART_FINAL);
        return quarterFinalsTeams;
    }
    @Override
    public void planningQuarterFinalsMatchs() throws MessagingException{
            List<Team> quarterFinalsTeams = qualifiedTeamsToQuarterFinals();
            List<Team> teamsCasa = quarterFinalsTeams.stream().filter(team -> team.getSite() == Site.CASABLANCA ).
                    collect(Collectors.toList());
            List<Team> teamsRabat= quarterFinalsTeams.stream().filter(team -> team.getSite() == Site.RABAT).
                collect(Collectors.toList());

            for(int i =0; i<teamsCasa.size()/2;i++){
                MatchDisputee matchDispute =
                        new MatchDisputee(Championship_State.QUART_FINAL, teamsCasa.get(i).getSite(), teamsCasa.get(i), teamsCasa.get(i+2));
                        matchService.addMatch(matchDispute);
            }
            for(int i =0; i<teamsRabat.size()/2;i++){
                MatchDisputee matchDispute =
                        new MatchDisputee(Championship_State.QUART_FINAL, teamsRabat.get(i).getSite(), teamsRabat.get(i), teamsRabat.get(i+2));
                matchService.addMatch(matchDispute);
            }
            emailService.sendEmailQualifiedTeams(quarterFinalsTeams, Championship_State.QUART_FINAL);
    }
    
    @Override
    public List<Team> qualifiedTeamsToSemiFinals(){
        List<Team> semiFinalsTeams =  teamService.getAllTeamsByStage(Championship_State.DEMI_FINAL);
        return semiFinalsTeams;
    }
    @Override
    public void planningSemiFinalsMatchs() throws MessagingException{
        List<Team> semiFinalsTeams = qualifiedTeamsToSemiFinals();
        List<Team> teamsCasa = semiFinalsTeams.stream().filter(team -> team.getSite() == Site.CASABLANCA ).
                collect(Collectors.toList());
        List<Team> teamsRabat= semiFinalsTeams.stream().filter(team -> team.getSite() == Site.RABAT).
                collect(Collectors.toList());

            MatchDisputee matchDispute =
                    new MatchDisputee(Championship_State.DEMI_FINAL, teamsCasa.get(1).getSite(), teamsCasa.get(0), teamsCasa.get(1));
            matchService.addMatch(matchDispute);

            MatchDisputee matchDispute1 =
                    new MatchDisputee(Championship_State.DEMI_FINAL, teamsRabat.get(1).getSite(), teamsRabat.get(0), teamsRabat.get(1));
            matchService.addMatch(matchDispute1);
            
            emailService.sendEmailQualifiedTeams(semiFinalsTeams, Championship_State.DEMI_FINAL);
        }

    @Override
    public List<Team> qualifiedTeamsToFinals(){
        List<Team> FinalsTeams =  teamService.getAllTeamsByStage(Championship_State.FINAL);
        return FinalsTeams;
    }
    @Override
    public void planningFinalsMatchs() throws MessagingException{
        List<Team> FinalsTeams = qualifiedTeamsToFinals();

        MatchDisputee matchDispute =
                new MatchDisputee(Championship_State.FINAL, Site.CASABLANCA, FinalsTeams.get(0), FinalsTeams.get(1));
        matchService.addMatch(matchDispute);
        
        emailService.sendEmailQualifiedTeams(FinalsTeams, Championship_State.FINAL);

    }


}

