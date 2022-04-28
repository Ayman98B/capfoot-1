package com.capgemini.capfoot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.capfoot.repository.MatchRepository;
import com.capgemini.capfoot.entity.MatchDisputee;
import org.springframework.stereotype.Service;

@Service
public class MatchServiceImpl implements MatchService{

	@Autowired
	MatchRepository matchRepository;
	
	@Override
	public List<MatchDisputee> getAllMatchs() {

		return matchRepository.findAll();
	}

	@Override
	public MatchDisputee getMatchById(Long id) {

		return matchRepository.findById(id).get();
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
