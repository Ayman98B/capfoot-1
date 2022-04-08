package com.capgemini.capfoot;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.capgemini.capfoot.entity.Match;
import com.capgemini.capfoot.service.MatchService;


@SpringBootApplication
public class CapfootApplication implements CommandLineRunner{

	@Autowired
	MatchService matchservice;
	
	public static void main(String[] args) {
		SpringApplication.run(CapfootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// Add new Match
		Match match = new Match();
		match.setPhaseDeGroupe(true);
		match.setPhaseEliminationDirecte(false);
		match.setDateDuMatch(new Date());
		match.setSite("match");
		matchservice.addMatch(match);
		
		// Get Cars
		System.out.println("Existed matchs: \n" + matchservice.getAllMatchs());
		
	}

}
