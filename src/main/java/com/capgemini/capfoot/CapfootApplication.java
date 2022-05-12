package com.capgemini.capfoot;

import com.capgemini.capfoot.entity.*;
import com.capgemini.capfoot.repository.ChampionshipRepo;
import com.capgemini.capfoot.repository.GroupRepository;
import com.capgemini.capfoot.repository.PlayerRepository;
import com.capgemini.capfoot.repository.TeamRepository;
import com.capgemini.capfoot.service.AdminService;
import com.capgemini.capfoot.service.ChampionshipService;
import com.capgemini.capfoot.service.EmailService;
import com.capgemini.capfoot.service.GroupTeamService;
import com.capgemini.capfoot.service.PlayerService;
import com.capgemini.capfoot.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.mail.MessagingException;


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
    
    @Autowired
    AdminService adminService;
    
    @Autowired
    PlayerService playerService;
    
    @Autowired
    EmailService emailService;
    public static void main(String[] args) {

        SpringApplication.run(CapfootApplication.class, args);
    }

    Admin admin = createAdmin();
    Championship capfoot = createChampionship(admin);

    List<Team> casaTeams = Arrays.asList(
            generateTeam(Site.CASABLANCA,"team_1"),generateTeam(Site.CASABLANCA,"team_2"),generateTeam(Site.CASABLANCA,"team_3"),generateTeam(Site.CASABLANCA,"team_4"),
            generateTeam(Site.CASABLANCA,"team_5"),generateTeam(Site.CASABLANCA,"team_6"),generateTeam(Site.CASABLANCA,"team_7"),generateTeam(Site.CASABLANCA,"team_8"),
            generateTeam(Site.CASABLANCA,"team_9"),generateTeam(Site.CASABLANCA,"team_10"),generateTeam(Site.CASABLANCA,"team_11"),generateTeam(Site.CASABLANCA,"team_12"),
            generateTeam(Site.CASABLANCA,"team_13"),generateTeam(Site.CASABLANCA,"team_14"),generateTeam(Site.CASABLANCA,"team_15"),generateTeam(Site.CASABLANCA,"team_16"));
    List<Team> rabatTeams = Arrays.asList(
            generateTeam(Site.RABAT,"team_r_1"),generateTeam(Site.RABAT,"team_r_2"),generateTeam(Site.RABAT,"team_r_3"),generateTeam(Site.RABAT,"team_r_4"),
            generateTeam(Site.RABAT,"team_r_5"),generateTeam(Site.RABAT,"team_r_6"),generateTeam(Site.RABAT,"team_r_7"),generateTeam(Site.RABAT,"team_r_8"),
            generateTeam(Site.RABAT,"team_r_9"),generateTeam(Site.RABAT,"team_r_10"),generateTeam(Site.RABAT,"team_r_11"),generateTeam(Site.RABAT,"team_r_12"),
            generateTeam(Site.RABAT,"team_r_13"),generateTeam(Site.RABAT,"team_r_14"),generateTeam(Site.RABAT,"team_r_15"),generateTeam(Site.RABAT,"team_r_16")
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

    private static Championship createChampionship(Admin admin){
        Championship capfoot = new Championship();
        capfoot.setLabel("Capfoot");
        capfoot.setStartDate(LocalDate.now());
        capfoot.setEndDate(LocalDate.of(2022,05,10));
        capfoot.setAdmin(admin);

        return capfoot;
    }
    
    private static Admin createAdmin(){
        Admin admin = new Admin();
        admin.setFirstName("admin");
        admin.setLastName("Admin");
        admin.setPassword("password");
        admin.setEmailAdress("email@gmail.com");
        admin.setChampionships(null);
        return admin;
    }

    private Team generateTeam(Site site, String name){
        Team team = new Team();
        team.setName(name);
        team.setSite(site);
        List players = new ArrayList<>();
        for(int i = 0 ; i<6;i++){
            Player p = new Player(
                    "full Name",
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
                "full Name",
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
    public void run(String... args) throws MessagingException {
    	adminService.createAdmin(admin);
        championshipService.createChampionship(capfoot);
        casaTeams.forEach(TeamCasa -> teamService.inscription(TeamCasa));
        rabatTeams.forEach(TeamRabat -> teamService.inscription(TeamRabat));
        groupRepository.saveAll(GROUPS);
        groupTeamService.launchDraw();
        
        System.out.println("Sending email ... ");
        emailService.sendEmail("o.intissar@mundiapolis.ma", "Test");
        System.out.println("Email sent... ");
		
		
        /*List<Team> teams = teamService.getAllTeamsByChampionat(2L);
        for(Team player: teams) {
            System.out.println("Team : " + player);
            }
        
        */
        
        /*
        List<Player> capitains = playerService.getAllCaptains();

        for(Player player: capitains) {
        System.out.println("Captain : " + player);
        }
        teamRepository.saveAll(CASA_TEAMS);
        teamRepository.saveAll(RABAT_TEAMS);
        groupTeamService.launchDraw();*/
    }

}
