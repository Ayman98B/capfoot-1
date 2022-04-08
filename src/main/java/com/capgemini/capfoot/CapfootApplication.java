package com.capgemini.capfoot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.capgemini.capfoot.model.Championat;
import com.capgemini.capfoot.model.Statut;
import com.capgemini.capfoot.repository.ChampionatRepo;

@SpringBootApplication
public class CapfootApplication implements CommandLineRunner {

	@Autowired
	private ChampionatRepo championatRepo;

	public static void main(String[] args) {
		SpringApplication.run(CapfootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		testChampionat();

	}

	public void testChampionat() {
		// Create Championat
		Championat championat = new Championat();
		championat.setLibelle("Cap du monde");
		championat.setStatut(Statut.INSCRIPTION);
		championatRepo.save(championat);
		System.out.println("Add championat : " + championat.toString());

		// Update Championat
		championat.setStatut(Statut.FINAL);
		championatRepo.save(championat);
		System.out.println("Update championat : " + championat.toString());

		// get championat
		Championat championatToFind = championatRepo.findById(championat.getId()).get();
		System.out.println("Get championat : " + championatToFind.toString());

		// Create and save new championat
		Championat newChampionat = new Championat();
		newChampionat.setLibelle("World cup");
		newChampionat.setStatut(Statut.INSCRIPTION);
		championatRepo.save(newChampionat);
		
		System.out.println("Get All championas : ");
		for (Championat champ: championatRepo.findAll()) {
			System.out.println("Get championat : " + champ.toString());
		}
	}
}
