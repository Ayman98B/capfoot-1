package com.capgemini.capfoot;

import com.capgemini.capfoot.entity.Groupe;
import com.capgemini.capfoot.entity.Team;
import com.capgemini.capfoot.repository.GroupRepository;
import com.capgemini.capfoot.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class CapfootApplication implements CommandLineRunner {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    GroupRepository groupRepository;

    public static void main(String[] args) {

        SpringApplication.run(CapfootApplication.class, args);
    }
    
    List<Team> CASA_TEAMS = Arrays.asList(
            buildTeamCasa("team_1"), buildTeamCasa("team_2"), buildTeamCasa("team_3"), buildTeamCasa("team_4"),
            buildTeamCasa("team_5"), buildTeamCasa("team_6"), buildTeamCasa("team_7"), buildTeamCasa("team_8"),
            buildTeamCasa("team_9"), buildTeamCasa("team_10"), buildTeamCasa("team_11"), buildTeamCasa("team_12"),
            buildTeamCasa("team_13"), buildTeamCasa("team_14"), buildTeamCasa("team_15"), buildTeamCasa("team_16"));

    List<Team> RABAT_TEAMS = Arrays.asList(
            buildTeamRabat("team_r_1"), buildTeamRabat("team_r_2"), buildTeamRabat("team_r_3"), buildTeamRabat("team_r_4"),
            buildTeamRabat("team_r_5"), buildTeamRabat("team_r_6"), buildTeamRabat("team_r_7"), buildTeamRabat("team_r_8"),
            buildTeamRabat("team_r_9"), buildTeamRabat("team_r_10"), buildTeamRabat("team_r_11"), buildTeamRabat("team_r_12"),
            buildTeamRabat("team_r_13"), buildTeamRabat("team_r_14"), buildTeamRabat("team_r_15"), buildTeamRabat("team_r_16"));

    //List<Groupe> GROUPS = Arrays.asList(buildGroup("A"), buildGroup("B"), buildGroup("C"), buildGroup("D"),
            //buildGroup("E"), buildGroup("F"), buildGroup("G"), buildGroup("H"));

    private static Team buildTeamCasa(String teamName) {

        return new Team(teamName, "Casa");
    }

    private static Team buildTeamRabat(String teamName) {

        return new Team(teamName, "RABAT");
    }

    //private static Groupe buildGroup(String groupName) {

        //return new Groupe(groupName);
    //}

    @Override
    public void run(String... args) {
        teamRepository.saveAll(CASA_TEAMS);
        teamRepository.saveAll(RABAT_TEAMS);
        //groupRepository.saveAll(GROUPS);
    }

}
