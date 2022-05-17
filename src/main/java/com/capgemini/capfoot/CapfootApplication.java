package com.capgemini.capfoot;

import com.capgemini.capfoot.entity.Player;
import com.capgemini.capfoot.entity.Site;
import com.capgemini.capfoot.entity.Team;
import com.capgemini.capfoot.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class CapfootApplication implements CommandLineRunner {

	@Autowired
	TeamService teamService;

	public static void main(String[] args) {

		SpringApplication.run(CapfootApplication.class, args);
	}

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



	private Team generateTeam(Site site, String name){
		Team team = new Team();
		team.setName(name);
		team.setSite(site);
		List players = new ArrayList<>();
		for(int i = 0 ; i<6;i++){
			Player p = new Player(
					"Full Name",
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
				"Full Name",
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

		casaTeams.forEach(TeamCasa -> {
			try {
				teamService.inscription(TeamCasa);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		});
		rabatTeams.forEach(TeamRabat -> {
			try {
				teamService.inscription(TeamRabat);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		});
		
	}
}

