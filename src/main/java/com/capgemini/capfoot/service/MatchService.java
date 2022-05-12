package com.capgemini.capfoot.service;

import com.capgemini.capfoot.entity.Championship_State;
import com.capgemini.capfoot.entity.MatchDisputee;

import java.util.List;

public interface MatchService {

	public List<MatchDisputee> getAllMatchs();

	public MatchDisputee getMatchById(Long id);

	public List<MatchDisputee> getMatchByStage(Championship_State stage);

	public MatchDisputee addMatch(MatchDisputee match);

	public void updateMatch(Long id, MatchDisputee match);

	public void deleteMatch(Long id);

	public MatchDisputee setTeams(Long id, MatchDisputee setTeam);

	public MatchDisputee updateMatchScore(Long id, MatchDisputee updateTeamsScore);

	public MatchDisputee updateMatchFinalScore(Long id, MatchDisputee matchFinalScore);

}
