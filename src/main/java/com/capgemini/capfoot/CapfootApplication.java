package com.capgemini.capfoot;

import java.util.Date;
import java.util.Optional;

import com.capgemini.capfoot.dao.JoueurRepository;
import com.capgemini.capfoot.entity.Joueur;
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
	@Autowired
	JoueurRepository joueurRepo;
	
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

		// testJouer
		testJoueur();
		
	}

	public void testJoueur() {

		// creation d'un nouveau joueur
		Joueur joueur = new Joueur();
		joueur.setNom("Messi");
		joueur.setPrenom("Lionel");
		joueur.setCin("BBXXXXX");
		joueur.setTel("06-XXXXXXX");
		joueur.setCaptain(true);
		joueurRepo.save(joueur);
		System.out.println("Ajout du joueur : " + joueur.toString());

		// Modification du joueur
		joueur.setTel("05-AAAAAA");
		joueur.setCaptain(false);
		joueurRepo.save(joueur);
		System.out.println("Modification du joueur : " + joueur.toString());

		// Trouver joueur avec son Id
		Optional<Joueur> j = joueurRepo.findById(joueur.getId());
		System.out.println("Joueur cherché : " + j.get().toString());

		// creation d'un nouveau joueur
		Joueur joueur2 = new Joueur();
		joueur2.setNom("Ronaldo");
		joueur2.setPrenom("Cristiano");
		joueur2.setCin("KKXXXXX");
		joueur2.setTel("06-XXXXXXX");
		joueur2.setCaptain(false);
		joueurRepo.save(joueur2);
		System.out.println("Ajout du joueur : " + joueur2.toString());

		// Supprimer joueur 2
		joueurRepo.deleteById(joueur2.getId());

	}

}
