package com.capgemini.capfoot.service;

import com.capgemini.capfoot.entity.Championship;
import com.capgemini.capfoot.entity.Groupe;
import com.capgemini.capfoot.repository.ChampionshipRepo;
import com.capgemini.capfoot.repository.GroupRepository;
import com.capgemini.capfoot.repository.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class ChampionshipServiceImpl implements ChampionshipService {

	@Autowired
	ChampionshipRepo championshipRepo;
	@Autowired
	EmailService emailService;
	@Autowired
	TeamRepository teamRepository;

	@Autowired
	GroupService groupService;

	@Autowired
	GroupRepository groupRepository;

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

			List<Groupe> GROUPS = Arrays.asList(
					groupService.buildGroup("A",newChamp),groupService.buildGroup("B",newChamp),
					groupService.buildGroup("C",newChamp), groupService.buildGroup("D",newChamp),
					groupService.buildGroup("E",newChamp), groupService.buildGroup("F",newChamp),
					groupService.buildGroup("G",newChamp), groupService.buildGroup("H",newChamp));
			groupRepository.saveAll(GROUPS);

		}
	}

	@Override
	public void updateChampionship(Championship updateChamp) {

			// if changement de statut: planification des matches, Tirage au sort, l'envoie
			// des email
			// if statut = groupe, planification des match
		if (updateChamp.getId() == null){
			log.warn("Vous ne pouvez pas modifier le tournoi car ID null" );
		}else {
			if(updateChamp.getStatut() != updateChamp.getStatut()) {
				System.out.println("Sending Email...");
				try {
					emailService.sendEmail(teamRepository.findById(updateChamp.getId()).get(), "Test", "test d'envoie de mail");
				} catch (MailException mailException) {
					mailException.getStackTrace();
				} catch (Exception e) {
					System.out.println("Erreur d'envoie d'email: " + e);
				}
			}
			championshipRepo.save(updateChamp);
		}
	}

	@Override
	public void deleteChampionship(Long id) {
		championshipRepo.deleteById(id);
  }
  

}
