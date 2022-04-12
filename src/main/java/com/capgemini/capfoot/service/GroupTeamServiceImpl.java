package com.capgemini.capfoot.service;

import com.capgemini.capfoot.entity.GroupTeam;
import com.capgemini.capfoot.entity.Groupe;
import com.capgemini.capfoot.entity.Team;
import com.capgemini.capfoot.repository.GroupRepository;
import com.capgemini.capfoot.repository.GroupTeamRepository;
import com.capgemini.capfoot.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupTeamServiceImpl implements  GroupTeamService{

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    GroupTeamRepository groupTeamRepository;

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
}
