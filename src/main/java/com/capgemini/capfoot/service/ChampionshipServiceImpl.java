package com.capgemini.capfoot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.capfoot.entity.Championship;
import com.capgemini.capfoot.repository.ChampionshipRepo;

@Service
public class ChampionshipServiceImpl implements ChampionshipService {

	@Autowired
	ChampionshipRepo championshipRepo;

	@Override
	public List<Championship> getAllChampionships() {
		return championshipRepo.findAll();
	}

	@Override
	public Championship getChampionshipById(Long idCamp) {
		return championshipRepo.findById(idCamp).get();
	}

	@Override
	public void createChampionship(Championship newChamp) {
		if (championshipRepo.findNbProgressTrue() == 0)
			championshipRepo.save(newChamp);
		else
			System.out.println("Vous ne pouvez pas ajouter le tournoi '" + newChamp.getLabel()
					+ "', il y a un autre tournoi en cours !!");

	}

	@Override
	public void updateChampionship(Championship ChampToEdit) {
		// if changement de statut: planification des matches, Tirage au sort, l'envoie
		// des email
		// if statut = groupe, planification des match

		championshipRepo.save(ChampToEdit);

	}

	@Override
	public void deleteChampionship(Long id) {
		championshipRepo.deleteById(id);
	}
}
