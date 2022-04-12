package com.capgemini.capfoot.service;

import java.util.List;

import com.capgemini.capfoot.entity.MatchDisputee;

public interface MatchService {

	public List<MatchDisputee> getAllMatchs();

	public MatchDisputee getMatchById(Long id);

	public MatchDisputee addMatch(MatchDisputee match);

	public void updateMatch(Long id, MatchDisputee match);

	public void deleteMatch(Long id);
}
