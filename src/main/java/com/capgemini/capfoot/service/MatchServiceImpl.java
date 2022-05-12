package com.capgemini.capfoot.service;

import com.capgemini.capfoot.entity.*;
import com.capgemini.capfoot.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchServiceImpl implements MatchService{

	@Autowired
	MatchRepository matchRepository;

	GroupTeamService groupTeamService;

	public MatchServiceImpl(@Lazy GroupTeamService groupTeamService){
		this.groupTeamService = groupTeamService;
	}
	
	@Override
	public List<MatchDisputee> getAllMatchs() {
		return matchRepository.findAll();
	}

	@Override
	public MatchDisputee getMatchById(Long id) {

		return matchRepository.findById(id).get();
	}

	@Override
	public List<MatchDisputee> getMatchByStage(Championship_State stage) {

		return matchRepository.findByStage(stage);
	}

	@Override
	public MatchDisputee addMatch(MatchDisputee match) {

		return matchRepository.save(match);
	}

	@Override
	public void updateMatch(Long id, MatchDisputee match) {
		matchRepository.save(match);
	}

	@Override
	public void deleteMatch(Long id) {
		matchRepository.deleteById(id);
		
	}

	@Override
	public MatchDisputee setTeams(Long id, MatchDisputee setTeam) {

		Optional<MatchDisputee> matchToUpdate = matchRepository.findById(id);
		if (matchToUpdate.isPresent()){
			matchToUpdate.get().setTeamHome(setTeam.getTeamHome());
			matchToUpdate.get().setTeamAway(setTeam.getTeamAway());
			return matchRepository.save(matchToUpdate.get());
		}
		return null;
	}

	@Override
	public MatchDisputee updateMatchScore(Long id, MatchDisputee updateTeamsScore) {

		Optional<MatchDisputee> matchUpdateScore = matchRepository.findById(id);

		if(matchUpdateScore.isPresent()){
			matchUpdateScore.get().setScoreAway(updateTeamsScore.getScoreAway());
			matchUpdateScore.get().setScoreHome(updateTeamsScore.getScoreHome());
			matchUpdateScore.get().setMatchState(updateTeamsScore.getMatchState());
			matchUpdateScore.get().setMatchDate(updateTeamsScore.getMatchDate());

			int scoreTeamHome = updateTeamsScore.getScoreHome();
			int scoreTeamAway = updateTeamsScore.getScoreAway();
			Team teamHome =  updateTeamsScore.getTeamHome();
			Team teamAway = updateTeamsScore.getTeamAway();

			GroupTeam groupByTeam = groupTeamService.getGroupByTeam(teamHome);
      
			int totalMatchs = groupByTeam.getNbDrawMatch() + groupByTeam.getNbWonMatch() + groupByTeam.getNbLossMatch();
			if(!matchUpdateScore.get().isUpdated()){
				if(updateTeamsScore.getMatchState() == Match_State.END  && totalMatchs < 3 && updateTeamsScore.getStage() == Championship_State.GROUPE) {
					matchUpdateScore.get().setUpdated(true);
					if (scoreTeamHome > scoreTeamAway) {
						groupTeamService.addWin(teamHome, groupByTeam.getGroup());
						groupTeamService.addLoss(teamAway, groupByTeam.getGroup());
					}
					if (scoreTeamHome < scoreTeamAway) {
						groupTeamService.addLoss(teamHome, groupByTeam.getGroup());
						groupTeamService.addWin(teamAway, groupByTeam.getGroup());
					}
					if (scoreTeamHome == scoreTeamAway) {
						groupTeamService.addDraw(teamHome, groupByTeam.getGroup());
						groupTeamService.addDraw(teamAway, groupByTeam.getGroup());
					}
				}
			} if(updateTeamsScore.getMatchState() ==Match_State.END && updateTeamsScore.getStage() == Championship_State.LAST_SEXTEEN){
				matchUpdateScore.get().setUpdated(true);
				groupTeamService.lastSexteenTeams();
			}

			else{
				System.out.println("Already updated");;
			}
			int[] scoreMatch = new int[2];
			scoreMatch[0] = updateTeamsScore.getScoreAway();
			scoreMatch[1] = updateTeamsScore.getScoreHome();
			matchUpdateScore.get().setScoreMatch(scoreMatch);
			return matchRepository.save(matchUpdateScore.get());
		}

		return null;
	}

	@Override
	public MatchDisputee updateMatchFinalScore(Long id, MatchDisputee teamsScore) {

		Optional<MatchDisputee> matchFinalScore = matchRepository.findById(id);

		if(matchFinalScore.isPresent()){
			int[] scoreMatch = new int[2];
			scoreMatch[0] = teamsScore.getScoreAway();
			scoreMatch[1] = teamsScore.getScoreHome();

			matchFinalScore.get().setScoreAway(teamsScore.getScoreAway());
			matchFinalScore.get().setScoreHome(teamsScore.getScoreHome());
			matchFinalScore.get().setScoreMatch(scoreMatch);
			return matchRepository.save(matchFinalScore.get());
		}

		return null;
	}

}
