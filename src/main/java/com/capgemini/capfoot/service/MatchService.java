package com.capgemini.capfoot.service;

import java.util.List;

import com.capgemini.capfoot.entity.Match;


public interface MatchService {

	public List<Match> getAllMatchs();
	public Match getMatchById(Long id);
	public Match addMatch(Match car);
	public void updateMatch(Long id,Match car);
	public void delateMatch(Long id);
}
