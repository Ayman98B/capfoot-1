package com.capgemini.capfoot.service;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Arrays;
import java.util.List;

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
	public Championship updateChampionship(Championship updateChamp) throws MessagingException {

		log.info("Entred update championship");
		if (!championshipRepo.findById(updateChamp.getId()).isPresent()) {
			log.error("Championship not found !");
			throw new ChampionshipNotFoundException(updateChamp.getId());

		} else {
			Championship oldChamp = championshipRepo.findById(updateChamp.getId()).get();
			if (oldChamp.getStatut() != updateChamp.getStatut()) {


				if(updateChamp.getStatut() == Championship_State.GROUPE){
					this.groupTeamService.launchDraw();
				}

				if(updateChamp.getStatut() == Championship_State.LAST_SIXTEEN){
					this.groupTeamService.qualifiedTeamsToLastSixteen();
				}
				if (updateChamp.getStatut() == Championship_State.QUART_FINAL) {
					this.groupTeamService.planningQuarterFinalsMatchs();
				}
				if (updateChamp.getStatut() == Championship_State.DEMI_FINAL) {
					this.groupTeamService.planningSemiFinalsMatchs();
				}
				if (updateChamp.getStatut() == Championship_State.FINAL) {
					this.groupTeamService.planningFinalsMatchs();
				}

				log.info("Sending Email ...");
				sendEmailNews(updateChamp);
				log.info("Email Sent ...");

			}
			return championshipRepo.save(updateChamp);
		}
	}

	public void sendEmailNews(Championship champ) {
		List<Team> teams = teamService.getAllTeamsByChampionat(champ.getId());
		try {
			emailService.sendEmailChampNewsToAll(teams, champ.getStatut());
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
