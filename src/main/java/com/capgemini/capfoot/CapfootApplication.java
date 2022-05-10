package com.capgemini.capfoot;

import com.capgemini.capfoot.entity.*;
import com.capgemini.capfoot.repository.ChampionshipRepo;
import com.capgemini.capfoot.repository.GroupRepository;
import com.capgemini.capfoot.repository.PlayerRepository;
import com.capgemini.capfoot.repository.TeamRepository;
import com.capgemini.capfoot.service.ChampionshipService;
import com.capgemini.capfoot.service.GroupTeamService;
import com.capgemini.capfoot.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class CapfootApplication implements CommandLineRunner {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    ChampionshipRepo championshipRepo;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    TeamService teamService;

    @Autowired
    GroupTeamService groupTeamService;

    @Autowired
    ChampionshipService championshipService;
    public static void main(String[] args) {

        SpringApplication.run(CapfootApplication.class, args);
    }

    Championship capfoot = createChampionship();

    List<Team> casaTeams = Arrays.asList(
            generateTeam(String.valueOf(Site.CASABLANCA),"team_1"),generateTeam(String.valueOf(Site.CASABLANCA),"team_2"),generateTeam(String.valueOf(Site.CASABLANCA),"team_3"),generateTeam(String.valueOf(Site.CASABLANCA),"team_4"),
            generateTeam(String.valueOf(Site.CASABLANCA),"team_5"),generateTeam(String.valueOf(Site.CASABLANCA),"team_6"),generateTeam(String.valueOf(Site.CASABLANCA),"team_7"),generateTeam(String.valueOf(Site.CASABLANCA),"team_8"),
            generateTeam(String.valueOf(Site.CASABLANCA),"team_9"),generateTeam(String.valueOf(Site.CASABLANCA),"team_10"),generateTeam(String.valueOf(Site.CASABLANCA),"team_11"),generateTeam(String.valueOf(Site.CASABLANCA),"team_12"),
            generateTeam(String.valueOf(Site.CASABLANCA),"team_13"),generateTeam(String.valueOf(Site.CASABLANCA),"team_14"),generateTeam(String.valueOf(Site.CASABLANCA),"team_15"),generateTeam(String.valueOf(Site.CASABLANCA),"team_16"));
    List<Team> rabatTeams = Arrays.asList(
            generateTeam("RABAT","team_r_1"),generateTeam("RABAT","team_r_2"),generateTeam("RABAT","team_r_3"),generateTeam("RABAT","team_r_4"),
            generateTeam("RABAT","team_r_5"),generateTeam("RABAT","team_r_6"),generateTeam("RABAT","team_r_7"),generateTeam("RABAT","team_r_8"),
            generateTeam("RABAT","team_r_9"),generateTeam("RABAT","team_r_10"),generateTeam("RABAT","team_r_11"),generateTeam("RABAT","team_r_12"),
            generateTeam("RABAT","team_r_13"),generateTeam("RABAT","team_r_14"),generateTeam("RABAT","team_r_15"),generateTeam("RABAT","team_r_16")
    );

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

    List<Groupe> GROUPS = Arrays.asList(buildGroup("A"), buildGroup("B"), buildGroup("C"), buildGroup("D"),
            buildGroup("E"), buildGroup("F"), buildGroup("G"), buildGroup("H"));


    private static Team buildTeamCasa(String teamName) {

        return new Team(teamName, Site.CASABLANCA);
    }

    private static Team buildTeamRabat(String teamName) {

        return new Team(teamName, Site.RABAT);
    }

    private static Groupe buildGroup(String groupName) {

        return new Groupe(groupName);
    }

    private static Championship createChampionship(){
        Championship capfoot = new Championship();
        capfoot.setLabel("Capfoot");
        capfoot.setStartDate(LocalDate.now());
        capfoot.setEndDate(LocalDate.of(2022,05,10));
        capfoot.setAdmin(null);

        return capfoot;
    }

    private Team generateTeam(String site, String name){
        Team team = new Team();
        team.setName(name);
        team.setSite(Site.valueOf(site));
        List players = new ArrayList<>();
        for(int i = 0 ; i<6;i++){
            Player p = new Player(
                    "first Name",
                    "last Name",
                    "cin",
                    "062104980",
                    "mail@cap.Com",
                    "123",
                    true,
                    false,
                    true,
                    null
                    );
            players.add(p);
        }
        Player p = new Player(
                "first Name",
                "last Name",
                "cin",
                "062104980",
                "mail@cap.Com",
                "123",
                true,
                true,
                true,
                null
        );
        players.add(p);
        team.setPlayers(players);
        return team;
    }

    @Override
    public void run(String... args) {
        championshipService.createChampionship(capfoot);
        casaTeams.forEach(TeamCasa -> teamService.inscription(TeamCasa));
        rabatTeams.forEach(TeamRabat -> teamService.inscription(TeamRabat));
        groupRepository.saveAll(GROUPS);
        groupTeamService.launchDraw();


        /*teamRepository.saveAll(CASA_TEAMS);
        teamRepository.saveAll(RABAT_TEAMS);
        groupTeamService.launchDraw();*/
    }

}
