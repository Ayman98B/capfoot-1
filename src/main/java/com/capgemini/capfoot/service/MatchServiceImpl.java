package com.capgemini.capfoot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.capfoot.repository.MatchRepository;
import com.capgemini.capfoot.entity.Match;

public class MatchServiceImpl implements MatchService{

	@Autowired
	MatchRepository matchRepository;
	
	@Override
	public List<Match> getAllMatchs() {
		return matchRepository.findAll();
	}

	@Override
	public Match getMatchById(Long id) {
		return matchRepository.findById(id).get();
	}

	@Override
	public Match addMatch(Match match) {
		return matchRepository.save(match);
	}

	@Override
	public void updateMatch(Long id, Match match) {
		matchRepository.save(match);
	}

	@Override
	public void deleteMatch(Long id) {
		matchRepository.deleteById(id);
		
	}

}
