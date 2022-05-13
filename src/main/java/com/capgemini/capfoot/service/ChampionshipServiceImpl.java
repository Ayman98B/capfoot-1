package com.capgemini.capfoot.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import com.capgemini.capfoot.entity.Championship;
import com.capgemini.capfoot.entity.Championship_State;
import com.capgemini.capfoot.entity.Groupe;
import com.capgemini.capfoot.entity.Team;
import com.capgemini.capfoot.exception.ChampionshipNotFoundException;
import com.capgemini.capfoot.repository.ChampionshipRepo;
import com.capgemini.capfoot.repository.GroupRepository;
import com.capgemini.capfoot.repository.TeamRepository;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@NoArgsConstructor
public class ChampionshipServiceImpl implements ChampionshipService {

	@Autowired
	ChampionshipRepo championshipRepo;

	@Autowired
	@Lazy
	private GroupTeamService groupTeamService;



	@Autowired
	EmailService emailService;
	@Autowired
	TeamRepository teamRepository;

	@Autowired
	TeamService teamService;
	@Autowired
	GroupService groupService;

	@Autowired
	GroupRepository groupRepository;

	@Autowired
	MatchService matchService;

	@Autowired
	PlayerService playerService;


	/*public ChampionshipServiceImpl(@Lazy GroupTeamService groupTeamService){
		this.groupTeamService = groupTeamService;
	}*/

	public ChampionshipServiceImpl(ChampionshipRepo championshipRepo) {
		this.championshipRepo = championshipRepo;
	}

	@Override
	public List<Championship> getAllChampionships() {
		log.info("Entred get all championships");
		return championshipRepo.findAll();
	}

	@Override
	public Championship getChampionshipById(Long idCamp) {
		log.info("Entred get championship by id");
		if (!championshipRepo.findById(idCamp).isPresent()) {
			log.error("Championship not found !");
			throw new ChampionshipNotFoundException(idCamp);
		} else {
			log.info("championship with id " + idCamp + "found !");
			return championshipRepo.findById(idCamp).get();
		}

	}

	@Override
	public Championship createChampionship(Championship newChamp) {
		log.info("Entred create championship");
		championshipRepo.save(newChamp);
		log.info("Championship entity created");

		List<Groupe> GROUPS = Arrays.asList(groupService.buildGroup("A", newChamp),
				groupService.buildGroup("B", newChamp), groupService.buildGroup("C", newChamp),
				groupService.buildGroup("D", newChamp), groupService.buildGroup("E", newChamp),
				groupService.buildGroup("F", newChamp), groupService.buildGroup("G", newChamp),
				groupService.buildGroup("H", newChamp));
		groupRepository.saveAll(GROUPS);
		log.info("GROUPS of Championship entity created");
		return newChamp;
	}

	@Override
	public Championship updateChampionship(Championship updateChamp) {

		log.info("Entred update championship");
		if (!championshipRepo.findById(updateChamp.getId()).isPresent()) {
			log.error("Championship not found !");
			throw new ChampionshipNotFoundException(updateChamp.getId());

		} else {
			Championship oldChamp = championshipRepo.findById(updateChamp.getId()).get();
			if (oldChamp.getStatut() != updateChamp.getStatut()) {
        
				log.info("Sending Email ...");
				sendEmail(oldChamp);
				log.info("Email Sent ...");

				if(updateChamp.getStatut() == Championship_State.LAST_SIXTEEN){
					this.groupTeamService.qualifiedTeamsToLastSixteen();
				}
				if(updateChamp.getStatut() == Championship_State.QUART_FINAL){
					this.groupTeamService.planningQuarterFinalsMatchs();
				}
				if(updateChamp.getStatut() == Championship_State.DEMI_FINAL){
					this.groupTeamService.planningSemiFinalsMatchs();
				}
				if(updateChamp.getStatut() == Championship_State.FINAL){
					this.groupTeamService.planningFinalsMatchs();
				}

			}
			return championshipRepo.save(updateChamp);
		}
	}

	public void sendEmail(Championship oldChamp) {
		List<Team> teams = teamService.getAllTeamsByChampionat(oldChamp.getId());
		try {
			emailService.sendEmailToAllPlayers(teams,
					"Cap du monde: Update et informations de la phase précédente et le planning des matches");
		} catch (MailException mailException) {
			mailException.getStackTrace();
		} catch (Exception e) {
			log.error("Erreur d'envoie d'email: " + e);
			e.printStackTrace();
		}
	}

	@Override
	public void deleteChampionship(Long id) {
		log.info("Entred delete championship");
		championshipRepo.deleteById(id);
	}

}
