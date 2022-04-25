package com.capgemini.capfoot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.capfoot.entity.Championship;
import com.capgemini.capfoot.repository.ChampionshipRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ChampionshipServiceImpl implements ChampionshipService {

	@Autowired
	ChampionshipRepo championshipRepo;

	public ChampionshipServiceImpl(ChampionshipRepo championshipRepo) {
		this.championshipRepo = championshipRepo;
	}

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
		if (championshipRepo.findNbProgressTrue() > 0)
			log.warn("Vous ne pouvez pas ajouter le tournoi '" + newChamp.getLabel()
					+ "', il y a un autre tournoi en cours !!");
		else {
			championshipRepo.save(newChamp);
			log.info("Championship entity created");
		}
	}

	@Override
	public void updateChampionship(Long id,Championship champion) {

		if (id == null){
			log.warn("Vous ne pouvez pas modifier le tournoi car ID null" );
		}else {
			Championship championship = championshipRepo.findById(id).get();
			championship.setLabel(champion.getLabel());
			championship.setStartDate(champion.getStartDate());
			championship.setProgress(champion.isProgress());
			championshipRepo.save(championship);
		}
	}

	@Override

	public void deleteChampionship(Long id) {
		championshipRepo.deleteById(id);
  }
  
}
